Prepare:
I. Create a MySQL databases
    1. With scripts (you need a root database permission):
	- in the src/main/resources/com/cuprum/server/config/db/shell/ path run restartdb-smallapp.sh
	- in the src/tes/resources/com/cuprum/server/config/db/shell/ path run restartdb-test.sh		
    2. Or by sql statements:
	-  command:
	    "
		create database $DB;
		grant all on $DB.* to $DB@localhost identified by '12345678';
		flush privileges;
	    "
	, where $DB are 'smallapp', 'test'.
II. Install Sun JDK 1.5 or 1.6
III. Install maven

Run:
Ia. type 'mvn gwt:gwt' (hosted mode)
Ib. or type 'mvn jetty:run-exploded' and use browser with the http://localhost:8080/ url (live)

Development:
I. type 'mvn eclipse:eclipse' to prepare an Eclipse project.
II. In the Eclipse: 
	- Window->Preferences->Java->Build Path->Classpath Variables->New->(Name: M2_REPO, Path: ~/.m2/repositories)
	- Import the project.