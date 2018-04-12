# WOL-Client (aka. Wake on Lan-Client)

WOL-Client is a java project meant to run on any raspberry pi. As the name implies, with this project you will be able to remotely boot or power off your computer/s from anywhere.

## Current status

This project is still a WIP. With that being said, please do not procced with the following instruction until further notice. s    

## Getting Started

WOL-Client is not a stand-alone service, is a client who runs in the background using cron's job. And as a client, it requires a backend server.   

### Prerequisites

- One Raspberry Pi (if you intent to run the service there).
- Java SE 7 and up although I recommend that you download the lattest release.
- Lattest version of nmap (https://nmap.org/).


### Installing  

You need to register an account at (TODO).  
Then, create a local folder anywhere on your raspberry called, WOL.
```
$ mkdir WOL
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

## Example Use

TODO

## Contributing

If you want to contribute to the project, you are more than welcome. Simply, fork the repository, commit your code and create a pull request.

## Versions
  - version 0.3.1-pre.alpha
  - version 0.2.7-pre.alpha
  - version 0.2.5-pre.alpha  

## Authors

* **George Mantellos** - *Main Author* - [geocfu](https://github.com/geocfu)

See also the list of [contributors](https://github.com/geocfu/WOL/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details
