# Report

## Beschrijving:
Een app voor vriendegroepen met categoriën waarbij je uitgaven kan invoeren en vervolgens wordt uitgereken welk persoon aan wie moet betalen en hoeveel.

## StartActivity.java
Variabelen: username, groupsname en categoriename. (deze zijn public static String)
UsersHelpers.Callback met bijbehorende methoden

Methoden:
Click_on_register : dan ga je naar RegisterActivity.java en bijbehorend scherm.
Click_on_login: dan wordt er gekeken of de gebruikersnaam wel bestaat en of het wachtwoord dan overeenkomt. Als dat zo is dan ga je naar MenuActivity.java en bijbehorend scherm. StartActivity.username wordt de naam van de ingelogde gebruiker.
OnBackPressed: dan wordt de app afgesloten.

## RegisterActivity.java
UsersHelpers.Callback met bijbehorende methoden

Methoden:
Click_on_register: dan wordt er gekeken of de gebruikersnaam al bestaat, of de wachtwoorden die de gebruiker invoert overeenkomen. En dat opgeslagen in de database. En dan naar MenuActivity.java met bijbehorend scherm. De user wordt opgeslagen in de online database users. En StartActivity.username wordt de naam van de geregistreerde gebruiker.

## UsersHelper.java
Hier worden de gebruikers van de online database gehaald en opgeslagen in een list.

## UsersPost.java
Hier wordt de gebruiker opgeslagen in de online database (de users database)
Met de variabelen: id (automatisch), password en username. 

## User.java
Object user met twee variabelen: username en password met de getters en de setters.

## MenuActivity.java

Methoden:
BottomNavigationClickListener: dan wordt er gekeken op welk icoontje gedrukt en het scherm wat daarbij hoort geopend.
MoveToCategorieFragment: methode voor andere classen zodat je naar categoriefragment kan gaan.
MoveToExpensesFragment: methode voor andere classen zodat je naar expensesfragment kan gaan.
OnBackPressed: dan wordt de app afgesloten.

## GroupsFragment.java
GroupsHelper.Callback met bijbehorende methoden

Methoden:
ListClickListener voor groupsList: als je op een groep klik dan wordt die groepsnaam opgeslagen in de StartActivity.groupsname en wordt MoveToCategorieFragment uit de MenuActivity aangeroepen.
Bij het laden van de groepen wordt er gekeken of de list leeg is. Als de list leeg is wordt er in een textView gezet dat er geen groepen zijn. 
ClickListener for addButton: dan ga je naar MakeGroupActivity. 

## GroupsHelper.java
Hier worden de groepen van de online database gehaald en opgeslagen in een list. 

## GroupsPost.java
Hier wordt een groep opgeslagen in de online database (de groups database)
Met de variabelen: id (automatisch), groupsname en participator.

## Group.java
Object groep met één variabele: groepsnaam met de getter en de setter.

## GroupsAdapter.java
Hier wordt de listView gevuld met alle groepen die opgeslagen zijn in de database groups waar de gebruiker in zit. Ook wordt er gekeken of er een groep is aangeklikt. Als deze is aangeklikt dan wordt de switchButton op true gezet. 

## MakeGroupActivity.java
UsersHelpers.Callback met bijbehorende methoden.
GroupsHelpers.Callback met bijbehorende methoden.
ParticipatorsHelpers.Callback met bijbehorende methoden.

Methoden:
Click_on_plus: dan wordt er gekeken of er een groepsnaam is ingevuld en een gebruiker. Anders krijg je een foutmelding hierover. Ook wordt er gekeken of de groepsnaam al bestaat. Anders krijg je ook een foutmelding hierover. Vervolgens wordt er gekeken of de ingevulde gebruiker wel bestaat en of deze al in de groep zit. Als die niet in de groep zit en wel bestaat dan wordt deze toegevoed aan de nieuwe groep. En in de online database.
Click_on_make: dan wordt er gekeken of de groepsnaam wel is ingevuld en of de groepsnaam al bestaat.  Bestaat die naam niet dan wordt deze toegevoegd aan de online database. Daarna ga je weer naar MenuActivity.

## CategorieFragment.java
CategorieHelper.Callback met bijbehorende methoden

Methoden:
ClickListener for addButton: Hier wordt een categorie toegevoegd. Als op de button geklikt wordt wordt er eerst gekeken of er wel een groep geselecteerd is in de GroupsFragment. En of er wel een groepsnaam is ingevuld en of deze groepsnaam al bestaat. Zo niet en er is een groep geselecteerd dan wordt deze groep toegevoegd aan de online database categories.
Bij categoriën laden wordt er gekeken of de list leeg is, als de list leeg is dan wordt er in een textView dat er geen categoriën zijn. Als er geen groep geselecteerd is in GroupsFragment dan wordt er gezegd er in een textView neergezet dat er geen groep geselecteerd is.
ClickListener for categorie: als er op een categorie geklikt wordt dan wordt StartActivity.categoriename de naam van de categorie waar op geklikt wordt en wordt de MoveToExpensesFragment methode uit de MenuActivity aangeroepen.

## CategorieHelper.java
Hier worden de categoriën van de online database gehaald en in een list gezet.

## CategoriePost.java
Hier wordt de categorie opgeslagen in de online database. (de categories database)
Met de variabelen: id (automatisch), groupsname, categoriename.

## Categorie.java
Object met twee variabelen: groupsname en categoriename met de getters en de setters.

## CategorieAdapter.java
Hier wordt de listView gevuld met alle categoriën uit de database categories die bij de geselecteerde groep horen. Als er een categorie geselecteerd is wat te zien is aan StartActivity.categoriename dan wordt voor deze categorie de switchButton op true gezet.

## ExpensesFragment.java
ExpensesHelper.Callback met bijbehorende methoden.

Methoden:
ClickListener for addButton: dan ga je naar MakeExpensesActivity.
Bij het laden van de uitgaven wordt er gekeken of er wel een groeps geselecteerd. Zo niet dan wordt in een textView gezet dat er geen groep geselecteerd. Datzelfe geldt voor een categorie. Als deze beide wel geselecteerd dan wordt er gekeken of de list leeg is. Als die leeg is dan wordt er in een textView gezet dat er geen uitgaven zijn.

## ExpensesHelper.java
Hier worden de uitgaven van de online database gehaald en in een list gezet.

## ExpensesPost.java
Hier wordt de uitgave opgeslagen in de online database (de database expenses)
Met de variabelen: id (automatisch), amount, groupsname, toWhat, categoriename, username.

## Expenses.java
Object met vijf variabelen: amount, groupsname, thoWhat, categoriename, username en de getters en setters.

## ExpensesAdapter.java
Hier wordt de listView gevuld met alle uitgaven uit de database expenses die bij de geselecteerde groep en categorie horen. Dit kan je zien aan de variabelen StartActivity.groupsname en StartActivity.categoriename. Als de uitgave gedaan is door de gebruiker dan wordt de naam van diegene veranderd in u in de listView. Dit kan gezien worden door StartActivity.username.
Het bedrag wordt afgerond op twee getallen achter de comma.

## MakeExpensesActivity.java
Methoden:
Click_on_make: eerst wordt er gekeken of er wel iets ingevuld is bij de variabelen. Zo ja dan wordt deze uitgaven opgeslagen in de online database expenses en ga je terug naar MenuActivity.java.

## PaymentsFragment.java
ParticipatorsHelper.Callback en bijbehorende methoden.
ExpensesHelper.Callback en bijbehorende methoden.

Methoden:
Calculate: hier wordt uitgerekend wie aan wie moet betalen en hoeveel en dat opgeslagen in een list. En als er geen groep geselecteerd is dan wordt er in een textView gezet dat er geen groep geselecteerd is. Datzelfde geldt voor categorie. En als de expensesList leeg is dan wordt er in de textView gezet dat er geen uitgaven zijn. 

## Payment.java
Object met drie variabelen: much, toWho en fromWho met de getters en de setters.

## PaymentsAdapter.java
Hier wordt de listView gevuld met alle betalingen die uitgerekend zijn door middel van de uitgaven die bij de geselecteerde groep en categorie horen. Als de betaling moet gedaan worden door de gebruiker of aan de gebruiker dan wordt de naam van de gebruiker veranderd in u. Dit kan gezien worden door StartActivity.username. Het bedrag wordt afgerond op twee getallen achter de comma.

## ParticipatorFragment.java
ParticipatorsHelper.Callback met bijbehorende methoden.
UsersHelper.Callbaack met bijbehorende methoden.

Methoden:
ClickListener for addButton: er wordt gekeken of een gebruikersnaam is ingevoerd. Als dat niet zo is dan een error met vul een gebruikersnaam in. Dan wordt er gekeken of de gebruikersnaam wel bestaat. Zo niet dan weer een error. En er wordt gekeken of de gebruiker al in de groep zit. Zo niet dan wordt de gebruiker toegevoegd aan de groep.
Bij het laden van de deelnemers in de groep wordt de deelnemer die de gebruiker is als laatst geplaatst. 

## ParticipatorsHelper,java
Hier worden de participators van de online database gehaald en in een list gezet.

## Participator.java
Object met één variabele: participator met de getter en setter.

## ParticipatorsAdapter.java
Hier wordt de listView gevuld met alle participators die behoren bij de geselecteerde groep. Als de participator de gebruiker is dan wordt die naam veranderd in u.

## Moeilijke dingen
Het moeilijkste om te maken vond ik de navigatiebottombar die ik heb. Het moeilijke hieraan was om ervoor te zorgen dat variabelen door te geven. En het maken opzich zelf wat ook een uitdaging. 
Daarnaast vond ik het programmeren van de betalingen ingewikkeld. 

## Beslissingen
Mijn eerste idee was om allemaal aparte schermen te maken, maar ik heb ervoor gekozen om een navigatiebottombar te maken. Omdat dit veel overzichtelijker is voor de gebruiker. 
Ook was mijn eerste idee ook een datumprikker eraan te koppelen. Maar omdat dit te veel werk was heb ik dat achterwege te laten.
Ik zou ook nog een rekeningnummer erbij doen. Maar omdat ik krap in de tijd kwam te zitten heb ik dat achterwege gelaten.

## Ideale wereld
In de ideale wereld zou ik een scrollbar hebben gemaakt voor een gebruiker toe te voegen. Zodat je bij elke letter die je type suggesties krijg van gebruikers die die letters hebben. Ook zou ik dan de rekeningnummer hebben toegevoegd. En profielfoto’s voor gebruikers en groepen en categoriën.
