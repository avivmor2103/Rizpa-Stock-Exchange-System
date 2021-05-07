# Rizpa-Stock-Exchange-System

Description of the project: 

Our system first handles the input from the user, 
for an interactive menu that offers the user which of the options he can choose.
The program receives input from the user in a loop,
until the user enters the number 6 in the menu to announce the end of 
the trade and performs a check for each of the inputs regarding its correctness.
First, we perform the absorption of the XML file into the system,
in order for the system to contain the relevant data, the existing shares offered to the customer. 
All this is done through the Engine.generated folder,
which executes the connectivity from the XML file to the program itself and imports the information.

Our system we made we created an operating engine where we do all the system processes that are not visible to the customer,
and separated from the menu component.

The connectivity between the system engine and the customer interface (menu) is done by an external component,
the DTO folder which connects them by channeling only the passage of information without the ability to change from the menu to the engine.
That is for the classes within the folder there are only 'getter' methods.


In the input tests we were required to check that in each of the options first it was required to check that a valid XML file had been pre-inserted. 
Therefore, we chose to hold 'flag' which when the first insertion of the XML file will get a value 'true',
and so, in each time a number different that 1 in the menu is always checked the value of the 'flag'.

System engine:
Our system engine is an interface that contains each of the methods that specify each of the options offered to the user in the menu.
We chose this form as an interface for the simple reason that the option of working through it is the most convenient,
and allows for a wide application of the material studied (use of interfaces).
For this interface we have created a class which implements the above interface.
In this class we hold this we hold members who allow us to move on a specific stock requested by the user, 
execute buy / sell orders for it and provide information for this stock.
In addition, we have members who create the connectivity between the system engine and the user interface menu and enable the transfer of information as a black box.
Within the implementation class of the interface, we have produced additional methods for testing input corrections and converting inputs to ENUM values.


Main classes:
Stocks Class - The main class that holds most of the data for lists of all the stocks in the system. 
	This class has a member where it holds a collection of values,
	we chose to use the Map data structure since in this structure we can set some key for each value entered,
	for us it is the Symbol of each share, and thus each share is different. 
	This class has many methods that perform input tests as to the correctness of the values according to the members of the current stock.
	For these methods we have implemented separate classes that generate user error messages as needed under the Exceptions folder.
	All of these are sent from the search engine directly to the user interface menu and conveys for it a message to the user about the correct errors of the inputs entered by him.
	This class makes extensive use of the class that is at its base, Stock, 
	which holds all the relevant values for each stock, such as current market price, symbol, etc.

Commands class - This is a class that supports the collection of commands
	, both sell and buy orders.
	We have chosen to implement them as a collection of linked lists.
	We chose this data structure because of its advantage when deleting an organ in the list,
	when transactions are made between pending orders and counter-orders received from the user.
	The class also performs matching functions between pending commands and counter-commands,
	as well as methods that sort each of the lists according to the type as needed (sales lists from the lowest to the highest price, etc.).
	
Beyond that there are other classes like ‘Transactions’ which hold a list of transactions by building data and linked lists for convenience and sorting by execution dates.
Overlists are created of course by a Transaction class which represents a transaction that takes place between two opposing orders.

Bonus: MKT command 
	We choose to create command MKT as an option for the user in the 'user interface menu' (UImenu). 
	The creation of the command happens in the engine of the system by using a switch-case for any case (MKT or LMT).
	That way we can create and match command in a different way from LMT command which makes the transaction by the price that the user asked for.
	
	
	
	

