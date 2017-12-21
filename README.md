## This is a WIP; this project is not ready. Do not procceed with the instructions mentioned bellow untill further notice!

# WOL (aka. Wake On Lan)

WOL is a java project meant to run on any raspberry pi. As the name implies, with this project you will be able to remotely boot or power off your computer/s from anywhere.

## Getting Started

WOL is not a stand-alone service, is a client who runs in the background using cron's job. And as a client, it requires a backend server.  
Lucky for you, the backend server is located [here](https://github.com/ShtHappens796/Wake-On-LAN) and is created by [ShtHappens796](https://github.com/ShtHappens796).  
The service can be used from anyone and of course **is free**. An option to host the backend server of yours is there. See the instructions
[here](https://github.com/ShtHappens796/Wake-On-LAN#instructions) on how.

### Prerequisites

```
A raspberry Pi.
Java SE 7 and up. Although I recommend that you download the lattest release.
```

### Installing

- #### Using the server backend we provide
##### Security over all! The only user-sensitive data we store is username, email and the MAC address/es. Passwords are irreversibly hashed and then stored.
So after clearing that out lets procceed.  

 You need to register an account at https://wol.sht.gr/register.  
Then, create a local folder anywhere on your raspberry called, WOL.
```
$ mkdir <WOL>
```
Go to that folder by typping,
```
$ cd WOL
```
Then, you need to download the package. To do so, run the following command
in Terminal.  
```
$ git clone https://github.com/geocfu/WOL.git WOL
```
Unzip the .zip package.  
 - For this purpose we are going to use the unzip command  
 To install the unzip command run the following (skip if already installed):
```
$ sudo apt-get install unzip
```  

 ```
 $ unzip WOL.zip -d WOL
 ```
 **By now, you should have a folder named WOL and inside that folder all the repository's files.**

 Run the UnitRegistration.java program to complete the registration and to bond your account with your raspberry's MAC address.  
 The bellow commands need to be run inside the WOL folder.
```
$ javac UnitRegistration.java
$ java UnitRegistration
```
After that, you will be prompted to input your username and password.  
**If everything went right, you should now have completed your account registration and you are ready to implement the client.**

 Now, it is time to implement the client to raspberry's cron jobs.
 Run the following command,
 ```
 $ * * * * * java ~/Desktop/WOL/WOL.java >/dev/null 2>&1
 ```

 **DONE, enjoy!**

- #### Running your own backend php Server

 For the installation and configuration of the server look [here](https://github.com/ShtHappens796/Wake-On-LAN).  

 After you have successfully build your server, follow the above mentioned instructions.

## Contributing

If you want to contribute to the project, you are more than welcome. Simply, fork the repository, commit your code and create a pull request. Dont forget to add your name to the [contributors](https://github.com/geocfu/WOL/contributors) list.

## Versions
  - version 0.0.1 - initial version  

## Authors

* **George Mantellos** - *Client* - [geocfu](https://github.com/geocfu)

See also the list of [contributors](https://github.com/geocfu/WOL/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
