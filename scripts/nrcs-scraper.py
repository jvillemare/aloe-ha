# http://www.wrc.udel.edu/de-flora/
from urllib import request, parse
from bs4 import BeautifulSoup
from time import sleep
import json
import sys

webpage = request.urlopen('https://plants.sc.egov.usda.gov/java/characteristics')
bytes = webpage.read()

html = bytes.decode('utf8')
webpage.close()

parsed_html = BeautifulSoup(html, 'html.parser')
plants = parsed_html.find_all('tr', attrs={'class':'rowon'})

num_of_plants = len(plants)
count = 1

plant_data = {}
missed_plants = []

print('Found ' + str(num_of_plants) + ' plants')

last_plant = None

for p in plants:
	last_plant = p

	if len(p.find_all('a')) == 1:
		continue

	print('Loading Plant ' + str(count) + '/' + str(num_of_plants) + ' (' + str(round((float(count)/num_of_plants) * 100, 2)) + '%)')
	print('...plant name: \'' + p.text + '\'')
	count += 1
	payload = {'symbol': p.find('th').text}

	profile = None
	characteristics = None

	# initialize outside while loop
	profile_html = None
	characteristics_html = None

	attempts = 10

	while attempts > 0:
		attempts -= 1
		profile = request.get('https://plants.sc.egov.usda.gov/core/profile', params=payload)
		characteristics = request.get('https://plants.sc.egov.usda.gov/java/charProfile', params=payload)

		try:
			profile_html = BeautifulSoup(profile, 'html.parser')
			characteristics_html = BeautifulSoup(characteristics, 'html.parser')
		except UnicodeDecodeError:
			print("UnicodeDecoderError: Error reading website response, trying again (" + str(attempts) + " attempts left)...")
			sleep(1) # server may be struggling
			continue
		except AttributeError:
			print("AttributeError: Error in processing website response, trying again (" + str(attempts) + " attempts left) ...")
			sleep(1)
			continue
		else:
			break

	if attempts == 0:
		print('Unable to load \'' + p.text + '\' plant, skipping...')
		missed_plants.append(p.text)
		continue

	profile_html = profile_html.find('table', attrs={'class': 'bordered', 'border': '0', 'cellspacing':'0', 'cellpadding':'0'}).find('tbody').find_all('tr')[2:]

	plant_info = {}
	plant_info['symbol'] = p.find('th').text
	plant_info['group'] = profile_html.find('th').text
	plant_info['family'] = profile_html.find('th').text
	plant_info['duration'] = profile_html.find('th').text
	plant_info['growth_habitat'] = profile_html.find('th').text
	plant_info['native_status'] = profile_html.find('th').text

	if last_plant != None:
		plant_info['alias'] = last_plant.find('td', attrs={'class': 'resultsind1'}).replace('<em>', '').replace('</em>', ' ')
	else:
		plant_info['alias'] = list()

	for i in range(0, len(plant_record), 2):
		clean_text = plant_record[i + 1].text
		clean_text = clean_text.replace(u"\u00A0", "")

		plant_info[plant_record[i].text] = clean_text

	plant_data[p.text] = plant_info
	last_plant = None
	print("...loaded plant")
	break
	sleep(1) # to not bombard the server with requests

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
