JSON To CSV Converter

This is a modified version of [json-to-csv](https://github.com/SEL-Columbia/json-to-csv) project


This code can be used for generating a flat CSV file from a list of JSON Objects.
The [JSONFlattener](https://github.com/Arkni/json-to-csv/blob/master/src/org/jsontocsv/parser/JsonFlattener.java) will create list of key-value pairs for the generated JSON.
The [CSVWriter](https://github.com/Arkni/json-to-csv/blob/master/src/org/jsontocsv/writer/CsvWriter.java) would write the key value pairs to the specified file.

For example, consider the JSON format:

````json
{
	"firstName": "Brahim",
	"lastName": "Arkni"
}
````

would generate a CSV as below:

| lastName | firstname |
-----------|------------
| Arkni    | Brahim    |

The column names would dynamically be generated based on the keys in the JSON object.

The sample output files can be seen [here](https://github.com/Arkni/json-to-csv/blob/master/files).