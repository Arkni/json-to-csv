#### JSON To CSV Converter

This is a modified version of [json-to-csv](https://github.com/SEL-Columbia/json-to-csv) project.

This code can be used for generating a flat CSV file from a list of JSON Objects.
The [JSONFlattener](https://github.com/Arkni/json-to-csv/blob/master/src/main/java/org/jsontocsv/parser/JSONFlattener.java) will create list of key-value pairs for the generated JSON.
The [CSVWriter](https://github.com/Arkni/json-to-csv/blob/master/src/main/java/org/jsontocsv/writer/CSVWriter.java) would write the key value pairs to the specified file.

#### Usage:

1 - Using a JSON String:

- JSON string:
```json
{
	"firstName": "Brahim",
	"lastName": "Arkni"
}
```

- JAVA code:
```java
/*
 * Parse a JSON String and convert it to CSV
 */
List<Map<String, String>> flatJson = JSONFlattener.parseJson(jsonString);
// Using the default separator ','
// If you want to use an other separator like ';' or '\t' use
// CSVWriter.getCSV(flatJSON, separator) method
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "files/sample_string.csv");
```

- CSV output:
```csv
lastName,firstname
Arkni,Brahim
```
2 - Using a JSON file:

- JSON file:

considering the file `simple.json` in `/files` directory, contains the following JSON
```json
[
    {
        "studentName": "Foo",
        "Age": "12",
        "subjects": [
            {
                "name": "English",
                "marks": "40"
            },
            {
                "name": "History",
                "marks": "50"
            }
        ]
    },
    {
        "studentName": "Bar",
        "Age": "12",
        "subjects": [
            {
                "name": "English",
                "marks": "40"
            },
            {
                "name": "History",
                "marks": "50"
            },
            {
                "name": "Science",
                "marks": "40"
            }
        ]
    },
    {
        "studentName": "Baz",
        "Age": "12",
        "subjects": []
    }
]
```

- JAVA code:
```java
/*
 *  Parse a JSON File and convert it to CSV
 */
// There is 2 methods to parse the JSON file
// 1- JSONFlattener#parseJson(File file):
//    This will use the default encoding UTF-8 to parse the given file.
// 2- JSONFlattener#parseJson(File file, String encoding):
//    This will parse the file using the specified character encoding.
flatJson = JSONFlattener.parseJson(new File("files/simple.json"), "UTF-8");
// Using ';' as separator
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, ";"), "files/sample_file.csv");
```
- CSV output (see [/files/sample_file.csv](https://github.com/Arkni/json-to-csv/blob/master/files/sample_file.csv) file):
```csv
Age;subjects[1].marks;subjects[1].name;subjects[2].marks;subjects[2].name;studentName;subjects[3].marks;subjects[3].name
12;40;English;50;History;Foo;;
12;40;English;50;History;Bar;40;Science
12;;;;;Baz;;
```

3 - Using a JSON returned from a URL:

- JAVA code:
```java
/*
 *  Parse JSON from URL and convert it to CSV
 */
// There is 2 methods to parse a JSON returned from a URI
// 1- JSONFlattener#parseJson(URI uri):
//    This will use the default encoding UTF-8 to parse the JSON returned from the given uri.
// 2- JSONFlattener#parseJson(URI uri, String encoding):
//    This will parse the JSON returned from the uri using the specified character encoding.
flatJson = JSONFlattener.parseJson(new URI("http://echo.jsontest.com/firstname/Brahim/lastName/Arkni"));
// Using '\t' as separator
CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, "\t"), "files/sample_uri.csv");
```

- JSON:

for this example, I used the web service [echo.jsontest.com](http://echo.jsontest.com) to echo a JSON object like the following
```json
{
	"firstName": "Brahim",
	"lastName": "Arkni"
}
```

- CSV output (see [/files/sample_uri.csv](https://github.com/Arkni/json-to-csv/blob/master/files/sample_file.csv) file):
```csv
lastName	firstname
Arkni		Brahim
```

#### N.B:
- The column names would dynamically be generated based on the keys in the JSON object.
- The order of the JSON keys will not preserved during JSON conversion to CSV, See this [StackOverFlow question](http://stackoverflow.com/questions/4515676/keep-the-order-of-the-json-keys-during-json-conversion-to-csv) for more information.


The sample output files can be seen [here](https://github.com/Arkni/json-to-csv/blob/master/files).

#### Licence
See the [LICENCE](https://github.com/Arkni/json-to-csv/blob/master/LICENCE) file.