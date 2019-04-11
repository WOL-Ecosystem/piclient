# Current Status
The project is being actively reworked including code refactoring and logic. As of now, the stable release is the v1.0.0.

# WOL-Client (aka. Wake on Lan-Client)

WOL-Client is a java project meant to run on any raspberry pi and/or computer. As the name implies, with this project you will be able to remotely boot or power off your computer/s from anywhere. WOL-Client is not a stand-alone service, is a client who runs in the background contacting WOL-Server's RESTful API using cron's job.

## :satellite: [WOL- Server (aka. Wake on Lan-Server)](https://github.com/geocfu/WOL-Server) :arrow_right:

WOL-Server is a RESTful API designed to work alongisde with the WOL-Client.  

## Getting Started  

### Prerequisites

- WOL-Server (https://github.com/geocfu/WOL-Server)
- One Raspberry Pi (if you intent to run the service there).
- Java SE 7 and up although I recommend that you download the lattest release.
- Lattest version of nmap (https://nmap.org/).
- Lattest version of etherwake (https://linux.die.net/man/8/ether-wake).


### Installing  

Create a local folder anywhere on your raspberry called, wol.
```
$ mkdir wol
```
Go to that folder by typping,
```
$ cd wol
```
Then, you need to download the package. To do so, run the following command
in Terminal.  
```
$ git clone https://github.com/geocfu/WOL-Client
```
 **By now, you should have a folder named wol and inside that folder all the repository's files.**

 Run the Client.java program to complete the registration and to bond your account with your raspberry's MAC address.  
```
$ cd WOL-Client/src/main/java
$ javac Client.java
$ java Client
```
**If everything went right, you should now have completed your account registration and you are ready to implement the client.**

 Now, it is time to implement the client to raspberry's cron jobs.  

 First, create a shell script inside wol folder that will run the Client when called from cron.
 ```
 $ cd
 $ cd wol
 $ nano wol.sh
 ```
 Inside the wol.sh, type the following.

 ```
 #!/bin/bash
 cd /home/pi/wol/WOL-Client/src/main/java/ &&
 java -cp . Client
 ```
 Then, exit nano and save the file.  

 After that, run the following command to log the cron job
 ```
 $ crontab -e
 $ * * * * * /bin/bash /home/pi/wol/WOL-Client/src/main/java/wol.sh
 ```

 **DONE, enjoy!**  

 After all this, all you have to do is, visit www.your-public-domain/wake.html, type the username nd the password of your account and the name of computer to wake. After a maximum time of one minute, the target computer will wake.

## Contributing

If you want to contribute to the project, you are more than welcome. Simply, fork the repository, commit your code and create a pull request.

## Versions
  - version 1.0.0

## Authors

* **George Mantellos** - *Main Author* - [geocfu](https://github.com/geocfu)
## Contributors

* **Jim Chloros** - [JIMCHLOROS](https://github.com/JIMCHLOROS)

See also the list of [contributors](https://github.com/geocfu/WOL/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details
