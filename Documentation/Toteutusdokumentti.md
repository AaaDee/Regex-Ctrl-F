# Toteutusdokumentti

## Rakenne

Karkealla tasolla ohjelma koostuu säännöllisten lausekkeiden tulkista, sekä tämän hyödyntämiseen tarkoitetusta yksinkertaisesta graafisesta käyttöliittymästä. Ohjelma on pyritty toteuttamaan MVC-mallin hengessä siten, että sovellustoiminnallisuus ja käyttöliittymä on pidetty erillään toisistaan, ja muun sovelluksen yhteys käyttöliittymään toteutetaan erillisen kontrolliluokan kautta.

Ohjelma on jaettu seuraaviin pakkauksiin:

* **regex** sisältää säännöllisen lauseiden tulkkiin liittyvän toiminnallisuuden. Yhteistominnasta muun sovelluksen kanssa vastaa luokka RegexMatcher, joka vastaa kokonaisuuden toteuttamisesta pakkauksen muiden luokkien avulla
* **io** sisältää tiedostojen lukemiseen käytetyn IoReader-luokan
* **ui** sisältää käyttöliittymän toteutuksen. Kommunikoinnista muun sovelluksen kanssa vastaa UiController-luokka.
* **util** sisältää toteutuksessa käytetyt itse tehdyt tietorakenteet
* **program** sisältää suoritettavan Main-luokan, sekä Program-luokan, joka koordinoi toiminnallisuutta käytttöliittymän ja sovelluksen välillä


### Luokkakaavio

under construction

## Algoritmin toteutus

under construction

## Aika- ja tilavaativuudet sekä suorituskyky

[Billen](https://arxiv.org/pdf/cs/0606116.pdf) (2018) mukaisesti algoritmin pahimman tapauksen tilavaativuus on *O(m)* ja aikavaativuus *O(nm)*, missä *m* on säännöllisen lausekkeen koko, ja *n* luettavan tekstin koko. Aika- ja tilavaativuudet voidaan perustella yleisellä tasolla seuraavasti:

### Tilavaativuus

Lausekkeesta muodostettava NFA koostuu tiloista (State) ja uloskäynneistä (Exit), jotka molemmat ovat luokkaa O(1). Muodostusvaiheessa (luokassa Nfa) jokainen lausekkeen merkki lisää NFA:han korkeintaan kaksi uutta tilaa, ja korkeintaan kaksi uutta tilaa. Jokainen merkki käydään läpi kerran ja vain kerran. Näin ollen tilojen ja uloskäyntien määrä on verrannollinen lausekkeen pituuteen ja tältä osin tilavaativuus on luokkaa *O(m)*.

Simulointivaiheessa (luokassa NfaSimulator) simulaattori pitää kirjaa tiloista, joissa automaatti voisi kullakin hetkellä olla. Edelliseen kappaleen mukaisesti automaatissa on korkeintaan *O(m)* tilaa, ja jokaisessa tilassa voidaan olla yhtäaikaisesti korkeintaan kerran (jos algoritmi on jo lisättävässä tilassa suoritushetkellä, tilaa ei lisätä enää uudestaan). Näin ollen myös tältä osalta tilavaativuus on luokkaa *O(m)* tapauksessa, jossa NFA on yhtäaikaisesti kaikissa NFA:n tiloissa.

Tämänhetkisessä toteutuksessa luettava teksti pidetään suorituksen aikana ohjelman muistissa käytännöllisyyden takia, mutta periaatteessa tälle ei ole algoritmin toiminnan kannalta tarvetta, vaan teksti voitaisiin tarvittaessa lukea vaikka merkki kerrallaan, koska se luetaan aina järjestyksessä eikä peruutuksia tehdä. Jos tekstin pitäminen muistissa halutaan kuitenkin lukea tilavaativuuteen, niin tällöin se olisi luokkaa O(m+n).


### Aikavaativuus

under construction

## Puutteet ja parannusehdotukset

under construction

## Lähteet

under construction
