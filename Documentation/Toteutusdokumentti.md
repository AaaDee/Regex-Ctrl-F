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

![Luokkakaavio](/Documentation/images/classDiagram.png "Luokkakaavio")

Luokkakaavioon on sisällytetty vain keskeisimmät luokat. Yksisuuntainen nuoli kuvaa sitä, että nuolen kärjessä oleva luokka/pakkaus hyödyntää toisessa päässä olevaa luokkaa/pakkausta, mutta ei tosin päin. Esimerkiksi regex-pakkaus hyödyntää util-pakkausta, mutta util ei tee regexillä mitään. Kaksisuuntainen nuoli taas tarkoittaa, että vuorovaikutusta on kumpaankin suuntaan. Esimerkiksi UiController ottaa Program-luokalta vastaan tietoa algoritmin tuloksista, ja puolestaan antaa Programille tietoa käyttäjän toiminnasta.

## Algoritmin toteutus

under construction

## Aika- ja tilavaativuudet sekä suorituskyky

[Billen](https://arxiv.org/pdf/cs/0606116.pdf) (2018) mukaisesti algoritmin pahimman tapauksen tilavaativuus on *O(m)* ja aikavaativuus *O(mn)*, missä *m* on säännöllisen lausekkeen koko, ja *n* luettavan tekstin koko. Aika- ja tilavaativuudet voidaan perustella yleisellä tasolla seuraavasti:

### Tilavaativuus

Lausekkeesta muodostettava NFA koostuu tiloista (State) ja uloskäynneistä (Exit), jotka molemmat ovat luokkaa O(1). Muodostusvaiheessa (luokassa Nfa) jokainen lausekkeen merkki lisää NFA:han korkeintaan kaksi uutta tilaa, ja korkeintaan kaksi uutta uloskäyntiä. Jokainen merkki käydään läpi kerran ja vain kerran. Lisäksi uloskäyntien suhteen meidän täytyy pitää kirjaa vain koko NFA:sta johtavista uloskäynneistä, joten meidän ei tarvitse välittää NFA:n keskellä olevien exitList-listojen oikeellisuudesta, eli voimme tarvittaessa muodostaa uuden uloskäyntilistan suoraan viittauksella vanhaan listaan (esim. List newExits = oldFragment.getExitList()). Näin ollen yhtä uloskäyntiä ei tallenneta moneen kertaan, vaan kokonaisuudessaan muistissa olevien uloskäyntien määrä on verrannollinen lausekkeen pituuteen. Kokonaisuudessaan siis muistissa olevien tilojen ja uloskäyntien määrä on verrannollinen lausekkeen pituuteen ja tältä osin tilavaativuus on luokkaa *O(m)*.

Simulointivaiheessa (luokassa NfaSimulator) simulaattori pitää kirjaa tiloista, joissa automaatti voisi kullakin hetkellä olla. Edelliseen kappaleen mukaisesti automaatissa on korkeintaan *O(m)* tilaa, ja jokaisessa tilassa voidaan olla yhtäaikaisesti korkeintaan kerran (jos algoritmi on jo lisättävässä tilassa suoritushetkellä, tilaa ei lisätä enää uudestaan). Näin ollen myös tältä osalta tilavaativuus on luokkaa *O(m)* tapauksessa, jossa simulaattori on mahdollisesti yhtäaikaisesti kaikissa NFA:n tiloissa.

Tavanomaisen Thompsonin algoritmin toteutuksen lisäksi joudumme pitämään kirjaa indeksistä, josta kuhunkin tämänhetkiseen tilaan on edetty, jotta voimme selvittää että mistä löydetty merkkijono on alkanut. Tämä lisäys vaatii yhden ylimääräisen kokonaisluvun tallentamista jokaisen kokonaisluvun yhteyteen, eli tilavaativuus ei muutu tavalliseen Thompsonin algoritmiin nähden.

Tämänhetkisessä toteutuksessa luettava teksti pidetään suorituksen aikana ohjelman muistissa käytännöllisyyden takia, mutta periaatteessa tälle ei ole algoritmin toiminnan kannalta tarvetta, vaan teksti voitaisiin tarvittaessa lukea vaikka merkki kerrallaan, koska se luetaan aina järjestyksessä eikä peruutuksia tehdä. Jos tekstin pitäminen muistissa halutaan kuitenkin lukea tilavaativuuteen, niin tällöin kokonaisvaativuus on luokkaa O(m+n).

### Aikavaativuus

Syötteen muuttaminen postfix-muotoon on *O(m)* -operaatio. Jokainen syötteen merkki lisätään pinoon ja poistetaan sieltä tasan kerran, ja nämä kummatkin ovat *O(1)* -operaatioita. Lisäksi syötteeseen voidaan lisätä korkeintaan *m* pistettä, mikä sisältyy myäs *O(m)* -vaativuusluokkaan.

Postfix-muotoisen syötteen muuntaminen NFA:ksi koostuu myös pääosin *O(1)* -operaatioista. Ajallisesti haastavin työvaihe yhden merkin lisäämisessä on pahimmassa tapauksessa uuden uloskäyntilistan muodostaminen, ja sekin voidaan tehdä useimmissa tapauksissa vanhan listan pohjalta. Poikkeuksena tästä on |-merkki, jolloin kaksi uloskäyntilistaa pitää liittää yhteen. Tilavaatimusanalyysin mukaisesti uloskäyntien suuruusluokka on *O(m)*, eli pahimmillaan yhden merkin käsittelyn aikavaativuus on luokkaa *O(m)*, ja koko merkkijonon käsitteleminen siis luokkaa *O($m^2$)*.

Simulaatiovaiheessa (NfaSimulator) simulaattoria ajetaan pahimmillaan *n* askelta, kun koko merkkijono käydään läpi. Kunkin askeleen aikana simulaattori voi olla korkeintaan *m* tilassa. Yhden tilan käsittely on pääosin *O(1)* -operaatio, eli yksi merkkien vertailu, kokonaislukujen vertailu (onko tila jo listassa) sekä yhden tilan lisääminen listan loppuun. Poikkeuksen tähän muodostavat NFA:n haarautumat, eli "S-tilat" tässä toteutuksessa. S-tilan lisääminen tarkoittaa sitä, että  haarauman molemmat haarat lisätään tilaluetteloon rekursiivisesti, mikä voi pahimmillaan olla *O(m)* operaatio, jos kaikki tilat joudutaan lisäämään. Simulaattori kuitenkin pitää kirjaa siitä, että onko tila jo lisätty tällä kierroksella luetteloon, joten jos S-tila on jo lisätty luetteloon, ei sitä tällä kierroksella lisätä enää uudestaan. Näin ollen simulaattorin yhdellä askeleella käsiteltävien tilalisäysten määrä on luokkaa *O(m)*, eli kokonaisuudessaan askeleen aikavaativuus on pahimmillaan luokkaa *O(m)*, ja koko simulaation vaativuus luokkaa *O(mn)*.

Aikavaativuuden osalta saadut enimmäisvaativuudet ovat siis *O($m^2$)* ja *O(mn)*. Näistä jälkimmäinen on suurempi on silloin kuin *n > m*, eli luettavan tekstin pituus on suurempi kuin säännöllisen lausekkeen pituus. Tätä voidaan pitää pääosin realistisena oletuksena, joten kokonaisvaativuusluokkaa *O(mn)* voidaan pitää perusteltuna, mutta erikoistapauksessa *n < m* kokonaisvaativuus on *O($m^2$)*


### Suorituskyky




## Puutteet ja parannusehdotukset

under construction

## Lähteet

* Bille 2018, New Algorithms for Regular Expression Matching. [Arxiv](https://arxiv.org/pdf/cs/0606116.pdf)
