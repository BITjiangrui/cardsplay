Cards Play Platform: use as back-end service for playing cards based on block chain: eos
====================================

### Modules

* Core: core models supply room service and table service
* Apps: can add TexasPoker, TwentyOnePoints and other apps based on Core module
* Access: use to communicate with front-end service using Netty
* AAA: security check for accounts

### How to be a decentration platform to prevent cheating in game
* when the game is start in each round, the result of shuffle cards will be encrypted and write to block chain, and players can recall their game by game id after the game is end.
* How to prevent other players cheat is nothing about block chain but implement in service logic, take win3cards as an example, we wont let people know his or others cards util the game is over, but if player want to see his cards, he need to double his bet in the game later.

### Detai design docs are here :
https://docs.google.com/presentation/d/1Qqx6JhxDl0lwots7DekjKlN6OcVKKlnrSwP6Aplp8iY/edit#slide=id.p
