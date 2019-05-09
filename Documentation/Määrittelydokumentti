# Regex-Ctrl-F -Määrittelydokumentti

## Ohjelman peruskuvaus ja ratkaistava ongelma

Ohjelman tarkoituksena on kertoa käyttäjälle, että löytyykö etsitty merkkijono annetusta tekstitiedostosta, ja jos löytyy, niin missä kohdassa tekstitiedostoa löytö tai löydökset ovat (vrt. selaimen tai tekstinkäsittelyohjelman ctrl-f -toiminnallisuus). Pelkkien yksinkertaisten merkkijonojen lisäksi ohjelma tarjoaa myös mahdollisuuden käyttää säännöllisiä lausekkeita erikoismerkkien ()+?*| avulla. Ohjelmassa on myös yksinkertainen graafinen käyttöliittymä, joka tarjoaa mahdollisuuden tekstitiedoston lukemiseen, haettavan merkkijonon syöttämiseen, sekä tulosten esittämiseen.

## Hyödynnettävät algoritmit sekä aika- ja tilavaativuudet.

Säännöllisten lausekkeiden tulkki toteutetaan Thompsonin algoritmilla (Thompson 1968, Cox 2007), jossa lausekkeesta muodostetaan epädeterministinen äärellinen automaatti, jota simuloidaan pinon avulla. Algoritmi on valittu toteutettavaksi, koska se on suhteellisesta yksinkertaisuudestaan huolimatta melko tehokas vaihtoehto (Cox 2007) säännöllisten lausekkeiden toteuttamiseen.

Algoritmin aikavaativuudeksi on arvioitu (Cox 2007) O(mn), missä m on annettun merkkijonon pituus, ja n on luettavan tekstitiedoston pituus. Tilavaatimuksen osalta algoritmin tulee pitää muistissa automaatin tilat, joiden suuruusluokka on m, sekä käsiteltävä tekstitiedosto. Kokonaisuudessaan tilavaativuus on siis luokkaa O(m+n). Näihin vaativuusluokkiin tulee sisältyä luonnollisesti myös toteutettavan pinoautomaatin vaativuudet, jotka ovat push- ja pop-operaatioiden osalta aikavaativuudeltaan O(1), ja tilavaativuudeltaan O(m).

Regex Ctrl-F:n toteutuksessa pyritään myös näihin vaativuusluokkiin. Java-toteutuksesta (ja erityisesti Javan merkkijonojen käsittelystä) johtuen on kuitenkin erittäin todennäköistä, että ohjelma toimii vaativuusluokkansa  puitteissa hitaasti edistyneempiin algoritmeihin verrattuna.

## Lähteet:
[Cox 2007](https://swtch.com/~rsc/regexp/regexp1.html), Regular Expression Matching Can Be Simple And Fast 
(but is slow in Java, Perl, PHP, Python, Ruby, ...)
[Thompson 1968](http://doi.acm.org/10.1145/363347.363387), Regular expression search algorithm
