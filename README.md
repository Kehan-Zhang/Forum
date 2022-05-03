# Forum Project

This is a forum web application written by java, using spring framework for backend, thymeleaf and datatable for frontend and Cloud Sql as database. 



Link of our web app: [Know-or-not Forum](https://forum-a5zcgcw6cq-uc.a.run.app/)

Link of our presentation: [Presentation Video](https://www.youtube.com/watch?v=yz_VVi5aCVM)



## Installation

* Download latest version of Maven (https://maven.apache.org/download.cgi) and install mvn command. 

* Install a Java 1.8 (or higher) JDK.

* Install intelliJ (or other IDE).



## Compilation

If you want to compile this project, you need to prepare an Google Cloud Account, and create a service account.

The sevice account should be assigned with the following roles:

* Cloud SQL Admin
* Cloud Storage for Firebase Admin
* Container Registry Service Agent
* Secret Manager Secret Accessor

After create the service account, you are supporsed to download you servcie account credential and add it as a Github repository secret named 'GCP_CREDENTIALS'. The secret will be used in github workflow.



Download the project

```shell
git pull origin master
```



Authorize gcloud to access the Cloud Platform with Google user credentials:

```shell
gcloud auth login
```



The project uses Cloud Sql as our database, you are suppose to create a database in GCP and create a user for it.

Get access to Cloud Sql:

```shell
gcloud sql connect project -u username
```

Change 'username' to your username and enter the matching password.



Run the springboot project locally:

```shell
./mvnw -DskipTests spring-boot:run
```

or

```shell
./mvnw spring-boot:run
```



## Deployment

### Docker deployment

1. Build image

```shell
docker build -t gcr.io/cs7346/forum .
```

2. Run the image in container

```shell
docker run -d -p 8080:8080
```

3. Push the image to Container Registry

```shell
docker push gcr.io/cs7346/forum 
```

Change 'cs7346/forum' to 'your-project-id/your-repsitory-name'



### Jib deployment

1. Build image and upload the image to Container Registry

```shell
./mvnw compile jib:build
```

or build docker image locally

```shell
./mvnw compile jib:dockerBuild
```



2. Deploy the project to Cloud Run

```
gcloud run deploy forum --image [gcr.io/cs7346/forum:latest](http://gcr.io/cs7346/forum:latest) --update-secrets=password=sql-password:1 --update-secrets=credential=credential:1
```

This project used GCP secrets. You are supposed to add two secrets in GCP and expose them as environment variables to the container.

* Cloud Sql password 

  ​	Secret name: sql-password

  ​	Environment variable name: password

  ​	Value: password of Cloud Sql user created before

* Service account credential

​			Secret name: credential

​			Environment variable name: credential

​			Value: credential of service account used for created before		



3. Cloud Run autoscaling

```
gcloud run services update forum —min-instances 0 —max-instances 10
```



### App Engine Deployment

You have to re-deploy each time you change the code

```shell
./mvnw -DskipTests package appengine:deploy
```

```shell
gcloud app deploy
```


