[![Build Status](https://travis-ci.org/islomar/parrotter.svg)](https://travis-ci.org/islomar/parrotter)

# Exercise

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

## How to run it
* You need **Java 8**.
* There are two options:
  * **Option 1**
    * First, run 'mvn clean package'
    * Then, run 'java -jar target/parrotter-jar-with-dependencies.jar'
  * **Option 2**
    * Just execute "run-parrotter.sh" or "run-parrotter.bat".
* Enjoy it.


##Features

Posting: Alice can publish messages to a personal timeline
```
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.
```

Reading: I can view Alice and Bob’s timelines
```
> Alice
I love the weather today (5 minutes ago)
```
```
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)
```

Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

```
> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)
```

```
> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
```

##Details
* The application must use the console for input and output.
* Users submit commands to the application. There are four commands. “posting”, “reading”, etc. are not part of the commands; commands always start with the user’s name.
 * posting: ```<user name> -> <message>```
 * reading: ```<user name>```
 * following: ```<user name> follows <another user>```
 * wall: ```<user name> wall```


* Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.
* Don’t bother making it work over a network or across processes. It can all be done in memory, assuming that users will all use the same terminal.
* Non-existing users should be created as they post their first message. Application should not start with a pre-defined list of users.
 

## Some personal notes
* I called it "**parrotter**", because in Spanish we use the expression "to talk like a parrot" for someone who talks a looot. And because "Twitter" was already taken.
* Some things that I learnt during this exercise:
  * I played around a little bit more with **Java 8 streams**.
  * I didn't know that Mockito had the **given()**. I had always used when() for that (which now I see it's wrong,it doesn't respect the "when" meaning in Given-When-Then of BDD).
  * I saw the "Crafted Design" video some days ago. I wanted to try that "**Interaction Driven-Design**" idea, with Actions, Services, Repositories, Domain Entities, Feature tests... I saw several benefits and I will probably keep on practicing about it.
* I tried to have some **immutability**, e.g. in lists returned or the Message objects.
* I focused on the "**happy path**".
* **KISS and YAGNI** (e.g. no interfaces for the Console), though probably with huge room for improvement.
* I didn't worry at all about **concurrency**.


## What I'm not proud about
* Having a "**Command**" interface with an "execute" method might lead to confusion: obviously it's not a real Command pattern (I don’t need to decouple the sender and the receiver and everything is executed in "real time").
* I tried to apply the **Outside-In TDD** approach... but I'm afraid that I failed miserably :-) I moved back and forth between Outside-In, Inside-Out, no TDD...
* The way it is decided which action to execute. I don't like much that "**CommandGenerator**".
* The **FollowUser** action. The User is updated because it's a reference, but neither a Service nor Repository is called.
* Having a "**utils**" package. Shame on me, but I didn't find a better name.
* Some other things that I'm not even aware of :-)

## What I have doubts about
* Having **two models**: User and Message. I wonder if it wouldn't be better just having a User entity and a Message VO which is part of the User (as a Set).
* It's kind of **anaemic domain**. But I didn't see passing a Repository for each User creation... I didn't see clear a better way to do it (though I know that it exists).
* I started using **Optional** for the case no User exists. I finally decided to use the Null Object Pattern, which I think leaves a clearer code (though probably that's not happy path and I shouldn't have worried anyway).
* Class **ShowUserWallService**: it's split from UserService and MessageService because it needs to access both "domains", both repositories... but maybe it fit in one of them (or even it should exist only one Service, maybe there is only one Domain here).
* Maybe the **enum** for the command types is not worthy and here it's enough with constants.



## TO DO:
* FollowUserShould es una castaña. No puedo dejarlo así.
* Review the levels of abstractions.
* Clean up comments, TODOs, etc.
* Checkstyle
* Findbugs
* Inspect code
* Organize imports
* Remove comments
* Review the FIVE, TWO... does it really improve anything?
* Create tag!! (1.0.0)
