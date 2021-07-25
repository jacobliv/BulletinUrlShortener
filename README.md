# BulletinUrlShortener


To run: 
- Clone repository
- Navigate to the root directory of the project in a CLI program that can run java
- run: `java -Dfile.encoding=windows-1252 -jar .\target\CodeTest-0.0.1-SNAPSHOT.jar`

If that does not work, you can run this from your IDE. I use Intelij. The main class is at `com.bulletinCodeTest.CodeTest.CodeTestApplication`.
You should be able to run the main function there.

## Endpoints to hit with a curl command/with the returned short url
### `POST http://localhost:8080/shortUrl`

Request Body:   
  `{ "fullUrl":"<long url goes here>" }`

Response Body:
```
{
"timeSinceCreation": "<time since creation>",
"fullUrl": "<fullUrl>",
"shortUrl": "<shortUrl>"

} 
```
  
### `GET http://localhost:8080/<shortUrlvalue>`

**No Request or response body since it will redirect you to the appropriate website**

<small>NOTE: This url will be returned after you save, and have it shortend </small>

### `GET http://localhost:8080/shortUrl/all`

**No Request Body**

Response Body: 

```
{
"status":{SUCCESS,FAILURE},
"urls":[<list of url objects>]
}
```
