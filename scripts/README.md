# Scripts

This folder contains all the scripts used to collect plant data from different plant databases.

 - `sunny-edge-plants-data-scraper.py`: The client provided a table in a Word doc of basic plant information. It was manually loaded into an Excel spreadsheet for easy parsing by Python into a JSON file
 - `npc-flora-scraper.py`: Loads plant data from Chesapeake Bay Native Plant Center http://www.nativeplantcenter.net/plants/
 - `nrcs-scraper.py`: Loads plant data from the Natural Resources Conservation Service https://plants.sc.egov.usda.gov/java/characteristics
 - `udel-flora-scraper.py`: Loads plant data from Flora of Delaware Online Database http://www.wrc.udel.edu/de-flora/

# Data

The Python scripts above are run, and the gathered information is compiled and dumped into a JSON file (`.json`) locally in this directory. The JSON files are then manually moved to their correct location (`GardenProject/src/main/resources/plantData/`).

# Educational Use Disclaimer

All this publicly viewable information is compiled and used explicitly for this non-commercial, educational application that we are developing for a university project.
