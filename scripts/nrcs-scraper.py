# http://www.wrc.udel.edu/de-flora/
import requests
from urllib.parse import urlencode
from bs4 import BeautifulSoup
from time import sleep
import json
import sys

webpage = requests.get('https://plants.sc.egov.usda.gov/java/characteristics')

parsed_html = BeautifulSoup(webpage.text, 'html.parser')
plants = parsed_html.find_all('tr', attrs={'class':'rowon'})

num_of_plants = len(plants)
count = 1

plant_data = {}
missed_plants = []

print('Found ' + str(num_of_plants) + ' plants')

last_plant = None

for p in plants:
	if len(p.find_all('a')) == 1 and last_plant == None:
		last_plant = p
		print('alias, skipping...')
		continue

	scientific_name = ""
	common_name = p.find_all('td')[1].text

	for sn_part in p.find_all('td')[0].find('a').find_all('em'):
		scientific_name = scientific_name + sn_part.text + ' '

	scientific_name = scientific_name.rstrip()

	print('Loading Plant ' + str(count) + '/' + str(num_of_plants) + ' (' + str(round((float(count)/num_of_plants) * 100, 2)) + '%)')
	print('...plant name: \'' + common_name + '\'')
	count += 1

	query = urlencode({'symbol': p.find('th').text})

	profile = None
	characteristics = None

	# initialize outside while loop
	profile_html = None
	characteristics_html = None

	attempts = 10

	while attempts > 0:
		attempts -= 1

		profile = requests.get('https://plants.sc.egov.usda.gov/core/profile?' + query)
		characteristics = requests.get('https://plants.sc.egov.usda.gov/java/charProfile?' + query)

		try:
			profile_html = BeautifulSoup(profile.text, 'html.parser')
			characteristics_html = BeautifulSoup(characteristics.text, 'html.parser')
			profile.close()
			characteristics.close()
		except UnicodeDecodeError:
			print("UnicodeDecoderError: Error reading website response, trying again (" + str(attempts) + " attempts left)...")
			sleep(1) # server may be struggling
			continue
		except AttributeError:
			print("AttributeError: Error in processing website response, trying again (" + str(attempts) + " attempts left) ...")
			sleep(1)
			continue
		else:
			if profile.status_code == 200 and characteristics.status_code == 200:
				break
			print('profile.status_code = ' + str(profile.status_code))
			print('characteristics.status_code = ' + str(characteristics.status_code))
			print('one of the status codes not okay. trying again...')

	if attempts == 0:
		print('Unable to load \'' + p.text + '\' plant, skipping...')
		missed_plants.append(p.text)
		continue

	profile_html = profile_html.find('table', attrs={'class': 'bordered', 'border': '0', 'cellspacing':'0', 'cellpadding':'0'}).find_all('tr')[2:]

	plant_info = {}
	plant_info['common_name'] = common_name
	plant_info['symbol'] = p.find('th').text
	plant_info['group'] = ' '.join(profile_html[0].find_all('td')[1].text.split())
	plant_info['family'] = ' '.join(profile_html[1].find_all('td')[1].text.split())
	plant_info['duration'] = ' '.join(profile_html[2].find_all('td')[1].text.split())
	plant_info['growth_habitat'] = ' '.join(profile_html[3].find_all('td')[1].text.split())
	plant_info['native_status'] = ' '.join(profile_html[4].find_all('td')[1].text.split())

	if last_plant != None:
		plant_info['alias'] = last_plant.find('td', attrs={'class': 'resultsind1'}).replace('<em>', '').replace('</em>', ' ')
		last_plant = None
	else:
		plant_info['alias'] = list()

	characteristics_html = characteristics_html.find_all('table', attrs={'cellpadding': '3'})[0].find_all('tr')

	category = None

	for i in range(0, len(characteristics_html)):
		if len(characteristics_html[i].find_all('td')) == 1:
			future_key = characteristics_html[i].find('td').text.encode('ascii', 'ignore').decode('ascii', 'ignore')

			if len(future_key) == 0:
				break

			plant_info[future_key] = dict()
			category = future_key
		else:
			plant_info[category][characteristics_html[i].find_all('td')[0].text.encode('ascii', 'ignore').decode('ascii', 'ignore')] = characteristics_html[i].find_all('td')[1].text

	plant_data[scientific_name] = plant_info
	print("...loaded plant")
	sleep(1) # to not bombard the server with requests

print(json.dumps(plant_data, indent=2))

with open('nrcs-data.json', 'w') as outfile:
	json.dump(plant_data, outfile)

print('Successfully dumped plants into json file')

if len(missed_plants) > 0:
	print('ERROR: Some plants were missed when scraping')
	print('BEGIN: Had trouble loading the following plants:')
	print(missed_plants)
	print('END: Had trouble loading the following plants')
	with open('udel-flora-scraper-errors.txt', 'w') as outfile:
		outfile.write('ERROR: Some plants were missed when scraping\n')
		outfile.write('BEGIN: Had trouble loading the following plants:\n')
		for p in missed_plants:
			outfile.write(p + "\n")
		outfile.write('END: Had trouble loading the following plants\n')
