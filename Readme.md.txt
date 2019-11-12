Microservice Architecture Assignment
	https://github.com/samritiarora/msa-services
The application has below Six services.

			Registry Server (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-eureka-server)
			Transaction Service (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-transaction-service)
			Checkbook Service (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-checkbook-service)
			User Account Service (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-user-account-service)
			Edge Gateway (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-zuul-gateway)
			Cloud Config Server (https://github.com/samritiarora/msa-services/tree/master/mService-assgnmnt-colud-config)	

1.) mService-assgnmnt-colud-config 
	This Git-Hub repository will act as a config server and all other projects are going to refer these properties via http URL for their config.
			URL: http://localhost:8887/msa-eureka-server/default
2.) mmService-assgnmnt-eureka-server
	This service act as registry for all other service.
			URL: http://localhost:8240/eureka
3.) mService-assgnmnt-zuul-gateway
			URL: http://localhost:8765/msa-user-service/api/users
4.) mService-assgnmnt-user-account-service
			URL: GET http://localhost:8220/api/users/{userID}
			     POST http://localhost:8220/api/users {"username":"somevalue","password":"somevalue"}
			     POST http://localhost:8220/api/account {"userId":"417869a7-4945-4bf0-8898-5d72fda2d430","branch":"somevalue","noOfCqBkIssued":"somevalue","accountType":"somevalue",balance:2000}

5.) mService-assgnmnt-transaction-service
			URL: GET http://localhost:8230/api/accounts/{userID}
			     POST http://localhost:8230/api/accounts/users/accounts {"userId":"417869a7-4945-4bf0-8898-5d72fda2d430","branch":"somevalue","noOfCqBkIssued":"somevalue","accountType":"somevalue",balance:2000}
			     POST http://localhost:8230/api/transaction/transfer {"from":"417869a7-4945-4bf0-8898-5d72fda2d430","to":"417869a7-4945-4bf0-8898-5d72fda2d430",ammount:2000}
			     POST http://localhost:8230/api/transaction/withdraw {"from":"417869a7-4945-4bf0-8898-5d72fda2d430",ammount:2000}
    			     POST http://localhost:8230/api/transaction/deposit {"to":"417869a7-4945-4bf0-8898-5d72fda2d430",ammount:2000}
	
6.) mService-assgnmnt-checkbook-service
			URL: GET http://localhost:8240/order/checkbook/{accountId}
 			     POST http://localhost:8240/order/checkbook/{"userId":"417869a7-4945-4bf0-8898-5d72fda2d430","accountId":"417869a7-4945-4bf0-8898-5d72fda2d430",ammount:2000}
			     PATCH http://localhost:8240/order/checkbook/{"status":"COMPLETED"}

7/) ConfigRepo: These are the properties that the config server would refer for properties of all other project.	
URL:	https://github.com/samritiarora/mService-assgnmnt-cloud-props


All the images has been pushed to docker hub.


