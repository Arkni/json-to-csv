/*
 * Copyright 2012-2014 Dristhi software
 * Copyright 2015 Arkni Brahim
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jsontocsv;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsontocsv.parser.JSONFlattener;
import org.jsontocsv.writer.CSVWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        /*
         *  Parse a JSON String and convert it to CSV
         */
        List<Map<String, String>> flatJson = JSONFlattener.parseJson(jsonString());
        // Using the default separator ','
        CSVWriter.writeToFile(CSVWriter.getCSV(flatJson), "files/sample_string.csv");

        /*
         *  Parse a JSON File and convert it to CSV
         */
        flatJson = JSONFlattener.parseJson(new File("files/simple.json"), "UTF-8");
        // Using ';' as separator
        CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, ";"), "files/sample_file.csv");

        /*
         *  Parse JSON from URL and convert it to CSV
         */
        flatJson = JSONFlattener.parseJson(new URI("http://echo.jsontest.com/firstname/Brahim/lastName/Arkni"));
        // Using '\t' as separator
        CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, "\t"), "files/sample_uri.csv");
	
	/*
         *  Parse a Large JSON File and convert it to CSV
         */
        flatJson = JSONFlattener.parseJson(new File("files/sample_large.json"), "UTF-8");
        // Using ';' as separator
        Set<String> header = CSVWriter.collectOrderedHeaders(flatJson);
        // the intention is generate a csv file with specific headers - not all
        CSVWriter.writeLargeFile(flatJson, ";", "files/sample_largeFile.csv", header);  
    }

    private static String jsonString() {
        return  "[" +
                "    {" +
                "        \"studentName\": \"Foo\"," +
                "        \"Age\": \"12\"," +
                "        \"subjects\": [" +
                "            {" +
                "                \"name\": \"English\"," +
                "                \"marks\": \"40\"" +
                "            }," +
                "            {" +
                "                \"name\": \"History\"," +
                "                \"marks\": \"50\"" +
                "            }" +
                "        ]" +
                "    }" +
                "]";
    }
}
