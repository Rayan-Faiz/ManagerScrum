# ManagerScrumX

Welcome to ManagerScrumX! This project is designed to facilitate project management using the Scrum methodology. Below, you will find instructions on how to use the application and deploy it in your environment.

## Getting Started

To begin using ManagerScrumX, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/Rayan-Faiz/ManagerScrum

2. Make sure you have Java and Maven installed on your system.

3. Navigate to the project directory:

   ```bash
   cd ManagerScrum

4. Build the project using Maven:
  
   ```bash
   mvn clean install

5. Once the build is successful, you can run the application:

   ```bash
   java -jar target/managerscrum4iir-0.0.1-SNAPSHOT.jar


6. Access the application through your web browser at `http://localhost:8080`.

## User Roles

ManagerScrumX supports two user roles:

- **Member:** Members have access to the dashboard to view the backlog, sprints, tasks, and project users. They cannot perform CRUD operations on project data.
- **Scrum Master:** Scrum Masters have additional privileges to add, update, and delete user stories, sprints, and tasks. They can also delete project users.

## Dashboard

### Member Dashboard

- View the backlog with user stories.
- View sprints with their names, dates, and associated tasks.
- View project users and their roles.

### Scrum Master Dashboard

- Add, update, or delete user stories.
- Add, update, or delete sprints and tasks.
- Delete project users.

## Continuous Integration (CI) with Jenkins

ManagerScrumX utilizes Jenkins for Continuous Integration (CI) with Maven. Follow these steps to set up CI:

1. Install Jenkins on your server.
2. Create a new Jenkins job.
3. Configure the job to pull the project's source code from your repository.
4. Set up the Jenkinsfile provided in the project for the pipeline.
5. Trigger the Jenkins job to execute the pipeline.

## Continuous Deployment (CD) with Docker

ManagerScrumX supports Continuous Deployment (CD) with Docker. Follow these steps to deploy the application using Docker:

1. Ensure Docker is installed on your deployment server.
2. Build a Docker image of the project:
   ```bash
   docker build -t managerscrum4iir-spring-app
3. Run a Docker container using the built image:
   ```bash
   docker-compose up -d

4. Access the application through your web browser at `http://<server_ip>:8080`.

## Conclusion

With ManagerScrumX, managing projects with the Scrum methodology becomes more streamlined. Whether you're a member or a Scrum Master, you have the tools to effectively organize and track project tasks. For continuous integration and deployment, Jenkins and Docker provide seamless automation, ensuring smooth development and deployment processes.

For any issues or questions, please refer to the project's documentation or reach out to the project maintainers. Happy project managing!
























