# campaign

## Tecnologies
    Java 8;
    MySql (default: root without pwd);
    Jdbi.
  
## How to Run
    1. Execute file queries.sql;
    2. Run CampaignApplication.java.
  
## How to Run Tests
    gradle test
  
## APIs
  
 ### InsertCampaign
    POST/Request URL: 
      localhost:8080/insertCampaign

    Request Body: 
      {
        "name": "campanha8",
        "teamId": 8,
        "startDate": "2017-05-05T13:10:00",
        "endDate": "2017-05-19T13:10:00"
      }

    Successful response-200OK:
      {
        "id": 11,
        "name": "campanha8",
        "teamId": 8,
        "startDate": "2017-05-05T13:10:00.000+0000",
        "endDate": "2017-05-22T13:10:00.000+0000"
      }
         
  ### DeleteCampaign
    GET/Request URL: 
      localhost:8080/deleteCampaign/1
      
    Successful response-200OK:
      {
        true
      }

  ### InsertClient
    POST/Request URL: 
      localhost:8080/insertClient

    Request Body: 
      {
        "name": "Stela",
        "email": "stela@gmail.com",
        "birthDate": "2015-06-11T15:00:00",
        "teamId": 1
      }
          
    Successful response-200OK:
      [
        {
            "id": 8,
            "name": "campanha8",
            "teamId": 3,
            "startDate": "2017-05-05T13:10:00.000+0000",
            "endDate": "2017-05-21T13:10:00.000+0000"
        },
        {
            "id": 9,
            "name": "campanha9",
            "teamId": 3,
            "startDate": "2017-05-05T13:10:00.000+0000",
            "endDate": "2017-05-20T13:10:00.000+0000"
        },
        {
            "id": 10,
            "name": "campanha10",
            "teamId": 3,
            "startDate": "2017-05-05T13:10:00.000+0000",
            "endDate": "2017-05-19T13:10:00.000+0000"
        }
      ]
 
  
  
  
