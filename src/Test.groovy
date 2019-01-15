//import groovy.grape.Grape
//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')

import groovyx.net.http.*
import groovyx.net.http.ContentType.*
import groovyx.net.http.Method.*
import net.sf.json.*


    def scibase
    scibase = new HTTPBuilder('http://localhost:8080')

    def newItemId

    def jsonBody = [:]
    jsonBody.put("title", "Test title")
    jsonBody.put("parentId", "4f5fc39de4b098845cbcb45e")


    jsonBody.put("subTitle", "subtitle")
    jsonBody.put("summary", "summary")
    jsonBody.put("body", "body")
    jsonBody.put("purpose", "purpose")
    jsonBody.put("rights", "rights")
    jsonBody.put("edition", "edition")
    jsonBody.put("materialRequestInstructions", "materialRequestInstructions")



    // POST
    scibase.request(Method.POST, ContentType.JSON){ req ->
        uri.path = "/updateproperties/testing/"
        //uri.query = [format:'json']
        body = jsonBody

        response.success = { resp, json ->
            assert resp.status == 200
            //newItemId = json.id
        }
        // not logged in response
        response.'302' = { resp ->
            throw new Exception("Stopping at item POST: uri: " + uri + "\n" +
                    "   You are not logging in properly. Item will not be created.")
        }
        response.failure = { resp, json ->
            throw new Exception("Stopping at item POST: uri: " + uri + "\n" +
                    "   Unknown error trying to create item: ${resp.status}, not creating Item." +
                    "\njson = ${json}")
        }
    }



