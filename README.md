# NewsApp
### Android Developer News APP Project.
A bit overview, it uses newsapi.org API to get news and get JSON data back. Then it is parsed using google's gson library and the requests are sent by Retrofit.

#### Note: To run the app you have to add [NewsAPI.org](https://newsapi.org/register) key. I have taken this step to due to limited access to daily request for News. Getting a key would take just few seconds :)

#### Setup API Key
1. Visit [NewsAPI.org](https://newsapi.org/register) to get your API Key
2. Copy your API Key from accounts section
3. Open `gradle.properties` *(Create the file if not exists)*
   - For Linux/Mac: `/home/.gradle/gradle.properties`
   - For Windows: `C:\Users\<UserName>\.gradle\gradle.properties`
   - Add the following line:
    ```
    NewsApi_ApiKey="YOUR_API_KEY"
    ``` 


#### Demo
<img src="newsAppDemo.mp4" width="40%">


#### Libraries
- [Google gson](https://github.com/google/gson) For parsing JSON data
- [Retrofit](http://square.github.io/retrofit/) For sending network requests
- [Glide](https://github.com/bumptech/glide) Loading and caching images
