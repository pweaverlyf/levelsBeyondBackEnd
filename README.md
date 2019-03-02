# levelsBeyondBackEnd

<strong>What you'll need</strong>
<ul>
  <li><a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">Java Development Kit 1.8</a> or later</li>
  <li><a href="https://gradle.org/install/">Gradle 4+</a> (I used the homebrew option - brew install gradle)</li>
</ul>

<strong>To get up and running</strong>
<ul>
  <li>In your chosen directory, clone this repo (git clone link-to-repo)</li>
  <li>From root of the project, run gradle build</li>
  <li>Run java -jar build/libs/gs-rest-service-0.1.0.jar</li>
  <li>Open your browser to localhost:8080/api/notes</li>
  <li>I recommend using Postman to test all CRUD operations</li>
 </ul>
 
 <strong>Routes Implemented</strong>
 <ul>
  <li>GET - localhost:8080/api/notes - returns all notes</li>
  <li>GET - localhost:8080/api/notes/{id} - returns note with id {id}</li>
  <li>POST - localhost:8080/api/notes - creates new note</li>
  <li>POST - localhost:8080/api/notes/{id} - updates note with id {id}</li>
  <li>DELETE - localhost:8080/api/notes{id} - deletes note with id {id}</li>
 </ul>
