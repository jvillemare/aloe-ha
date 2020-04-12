# http://www.wrc.udel.edu/de-flora/
from urllib import request, parse
from bs4 import BeautifulSoup
from time import sleep
import json

webpage = request.urlopen("http://www.wrc.udel.edu/de-flora/")
bytes = webpage.read()

html = bytes.decode("utf8")
webpage.close()

parsed_html = BeautifulSoup(html, "html.parser")
plants = parsed_html.find('select', attrs={'name':'scientific_name'}).find_all('option')

num_of_plants = len(plants)
count = 1

plant_data = {}
missed_plants = []

for p in plants:
	if p.text == "" or p.text == "Select by Scientific name":
		print("Skipping invalid plant names...")
		continue

	print("Loading Plant " + str(count) + "/" + str(num_of_plants) + " (" + str(round((float(count)/num_of_plants) * 100, 2)) + "%)")
	print("...plant name: '" + p.text + "'")
	count += 1
	post_data = {"criteria": "scientific_name", "critcount": 1, \
	"value_text": p.text, "val": p.text, "level": 0, "sort_by": "s", "results_per_page": ""}
	post_data = parse.urlencode(post_data).encode()

	my_request = request.Request("http://www.wrc.udel.edu/wp-content/heritage/flora-res.php", data=post_data)
	my_response = request.urlopen(my_request)

	# initialize outside while loop
	plant_html = 0
	plant_record = 0
	attempts = 10

	while attempts > 0:
		attempts -= 1
		try:
			plant_html = my_response.read().decode("utf8")
			my_response.close()
			plant_html = BeautifulSoup(plant_html, "html.parser")
			plant_record = plant_html.find('div', attrs={'id':'record0'}).find_all('div')
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
		print("Unable to load '" + p.text + "' plant, skipping...")
		missed_plants.append(p.text)
		continue

	plant_info = {}

	for i in range(0, len(plant_record), 2):
		clean_text = plant_record[i + 1].text
		clean_text = clean_text.replace(u"\u00A0", "")

		plant_info[plant_record[i].text] = clean_text

	plant_data[p.text] = plant_info
	print("...loaded plant")
	sleep(1) # to not bombard the server with requests

with open('GardenProject/src/main/udel/plants/data/udel-flora.json', 'w') as outfile:
	json.dump(plant_data, outfile)

print("Successfully dumped plants into json file")

if len(missed_plants) > 0:
	print("ERROR: Some plants were missed when scraping")
	print("BEGIN: Had trouble loading the following plants:")
	print(missed_plants)
	print("END: Had trouble loading the following plants")
	with open('udel-flora-scraper-errors.txt', 'w') as outfile:
		outfile.write("ERROR: Some plants were missed when scraping\n")
		outfile.write("BEGIN: Had trouble loading the following plants:\n")
		for p in missed_plants:
			outfile.write(p + "\n")
		outfile.write("END: Had trouble loading the following plants\n")
