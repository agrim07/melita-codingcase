# Machine health processor Service

An order specifies the customer details (including personal details, installation address, preferred
installation date + time slot details), the required products (e.g. Internet, TV, Telephony, Mobile) and
the required package per product (e.g. Internet 250Mbps or 1Gbps, TV with 90 Channels or 140
Channels, Telephony with Free On net Calls or Unlimited Calls, Mobile Prepaid or Mobile Postpaid). The Order Taking API must validate these details and accept the order. On accepting the
order it must publish a messaging event to a RabbitMQ topic for Melita's Ordering Fulfilment system
and Care systems to pick it up.
###This application having two end-points:

1) /v2/authenticate - This end-point takes input username and password and return JWT token after validating user against in-memory SQLLite database.
   Request Format: 

   {
   "username": "Amaresh",
   "password": "Dhankhar"
   } 
2) /v2/order - This end-point used JWT token returned by /v2/authenticate end-point to place a order event into RabitMq.
   Request Format:
   
    {
   "customerDetail": {"uniqueid":"123", "name": "Amaresh", "phone": "+919873107252","email": "dhankhar.amaresh@gmail.com", "address": {"street1":"Street number - 1", "city": "Delhi", "country": "India", "zipCode": "122101"}},
   "installationDetail": {"date": "12-12-2022", "time" : "1pm - 2pm", "address": {"street1":"Street number - 1", "city": "Delhi", "country": "India", "zipCode": "122101"}},
   "products": "Internet",
   "package": "250Mbps"
   } 
