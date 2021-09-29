# Introduction
The app called Grep App because it implements the use of grep command in Linux
which will scans recursively files in a directory and then return a sequence of 
strings that match the input pattern. The technologies used including IntelliJ, 
Java Core, Maven, Lambda and Stream APIs and Docker.  
The app can handle over-sized files thanks to the help of Stream API. 
It was tested manually and then packaged into an uber jar file using Maven. 
The file was then used to create docker image and published to Docker Hub.

# Quick Start
- Run using jar file:  
java -jar path/to/the/file "pattern" path/to/search/directory "path/to/output/file"  
E.g. java -jar /home/grep-app.jar ^abc ./data /result/output.txt 
  
- Run using docker:  
docker run -rm -v host/path/to/scan:/data -v host/path/for/output/file:/out 
phuongly/grep "place pattern here" /data /out/grep.out.txt

#Implemenation
## Pseudocode
matchedLines = []  
&nbsp;&nbsp;for file in listFilesRecursively(rootDir)  
&nbsp;&nbsp;&nbsp;&nbsp;for line in readLines(file)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if containsPattern(line)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;matchedLines.add(line)  
writeToFile(matchedLines)

## Performance Issue
At the beginning, Scanner was used to read files' content which then become
troublesome when dealing with big-size files. The problem then was solved 
by using Stream API to reduce the amount of memory needed for a file
to load.

# Test
1. I created a text file with the content of Shakespeare poetry inside a nested 
directory (/data/txt/shakespeare.txt).
2. Running the grep app using IntelliJ (with and without the arguments)
3. Check the output file for comparing the result manually.  
Note: uber jar and docker image files also been tested manually
# Deployment
For easier distribution, I made a docker image out of the uber jar file
and published in to DockerHub. Check steps below:
1. Generate a file named Dockerfile and its content using below cmd:  
"cat > Dockerfile << EOF  
   FROM openjdk:8-alpine  
   COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar  
   ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]  
   EOF"
2. Run the file with below cmd:  
"docker build -t phuongly/grep ."
3. Login to Docker Hub and "docker push phuongly/grep" to publish 
the image.

# Improvement
1. Add some logger.info statements for logging information when the app running.
2. Make the exceptions to me more specific.
3. Test the app more using Junit