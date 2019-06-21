# Toteutusdokumentti

## Rakenne

Karkealla tasolla ohjelma koostuu säännöllisten lausekkeiden tulkista, sekä tämän hyödyntämiseen tarkoitetusta yksinkertaisesta graafisesta käyttöliittymästä. Ohjelma on pyritty toteuttamaan MVC-mallin hengessä siten, että sovellustoiminnallisuus ja käyttöliittymä on pidetty erillään toisistaan, ja muun sovelluksen yhteys käyttöliittymään toteutetaan erillisen kontrolliluokan kautta.

Ohjelma on jaettu seuraaviin pakkauksiin:

* **regex** sisältää säännöllisen lauseiden tulkkiin liittyvän toiminnallisuuden. Yhteistominnasta muun sovelluksen kanssa vastaa luokka RegexMatcher, joka vastaa kokonaisuuden toteuttamisesta pakkauksen muiden luokkien avulla.
* **io** sisältää tiedostojen lukemiseen käytetyn IoReader-luokan.
* **ui** sisältää käyttöliittymän toteutuksen. Kommunikoinnista muun sovelluksen kanssa vastaa UiController-luokka.
* **util** sisältää toteutuksessa käytetyt itse tehdyt tietorakenteet.
* **program** sisältää suoritettavan Main-luokan, sekä Program-luokan, joka koordinoi toiminnallisuutta käytttöliittymän ja sovelluksen välillä.

Ohjelmakoodi on kirjoitettu Googlen [Java-tyylioppaan](https://google.github.io/styleguide/javaguide.html) mukaisesti pienin muutoksin (joitain sisennyssyvyyksiä on muutettu) Checkstylen avulla. Koodi on pyritty pitämään mahdollisimman helppolukuisena, ja lisäksi kaikki luokat ja näiden metodit on dokumentoitu Javadoceilla. Mikäli Javadocit eivät ole näkyvissä, ne voidaan kirjoittaa uudestaan komennolla *gradle javadoc*.

### Luokkakaavio

![Luokkakaavio](/Documentation/images/classDiagram.png "Luokkakaavio")

Luokkakaavioon on sisällytetty vain keskeisimmät luokat. Yksisuuntainen nuoli kuvaa sitä, että nuolen kärjessä oleva luokka/pakkaus hyödyntää toisessa päässä olevaa luokkaa/pakkausta, mutta ei tosin päin. Esimerkiksi regex-pakkaus hyödyntää util-pakkausta, mutta util ei tee regexillä mitään. Kaksisuuntainen nuoli taas tarkoittaa, että vuorovaikutusta on kumpaankin suuntaan. Esimerkiksi UiController ottaa Program-luokalta vastaan tietoa algoritmin tuloksista, ja puolestaan antaa Programille tietoa käyttäjän toiminnasta.

## Algoritmin toteutus

Toteutettu säännöllisten lauseke perustuu Thompsonin (1968) algoritmiin ja erityisesti tästä tehtyyn Coxin (200x) toteutukseen. Algoritmin perusajatuksena on muuttaa lauseke epädeterministiseksi tila-automaatiksi (jatkossa NFA), jota simuloidaan askel kerrallaan käyttäen automaatin syötteenä tekstitiedostoa, josta lauseketta haetaan. 

Yksityiskohtaisemmalla tasolla algoritmi etenee seuraavasti:

Ensin säännöllinen lauseke muutetaan helpommin koneluettavaan postfix-muotoon, jossa operaattori tulee aina operandien jälkeen (esim. a|b olisi ab|). Tämä muunnos tapahtuu luokassa InfixToPostFixParser. Muunnoksessa käytetään Shunting-yard -algoritmia [LINKKI], joka karkeasti ottaen toimii niin, että säännöllinen lauseke käydään läpi kerran, käsiteltävän lausekkeen operaattorit viedään erilliseen operaattoripinoon, ja käsittelyn edessä operaattoripinoa tyhjennetään operaattorien keskenäisen prioriteettijärjestyksen mukaisesti.

Tämän jälkeen säännöllinen lauseke muutetaan NFA:ksi merkki kerrallaan. Karkeasti otatten muista kuin operaattorimerkeistä tulee NFA:n tiloja, ja operaattorimerkit vastaavat siirtymistä tilojen välillä (eli NFA:n "nuolista"). Lisäksi käytössä on erikoistila *S*, joka tarkoittaa sitä, että NFA haarautuu kyseisessä kohdassa, ja erikoistila *M*, joka tarkoittaa että haettu merkkijono on löytynyt. NFA:n luominen tapahuu luokassa Nfa.

Lopuksi NFA:ta simuloidaan käyttäen syötteenä tekstiä, josta haku tehdään. Päävastuussa on luokka NfaSimulator. Jokaisella syötteen merkillä NFA:ta viedään yksi askel eteenpäin, ja algoritmi pitää kirjaa tiloista, joissa NFA voi tällä hetkellä olla. Kunkin tilan kohdalla pidetään kirjaa kahdesta asiasta:
* Milloin tila on lisätty viimeksi mahdollisten tilojen listaan (jottei lisäystä tehdä monta kertaa kierroksessa)
* Jos kyseinen tila johtaisi osumaan, mikä on sen merkin indeksi, josta osuma alkoi. Käytännössä tästä pidetään kirjaa siten, että lähtötilojen tapauksessa indeksi on senhetkisen simulointikierroksen numero, ja muussa tapauksessa indeksi saadaan lisättävän tilan edeltäjältä NFA:ssa.

Algoritmia ajetaan niin kauan, kunnes joko osuma on löytynt tai koko syöte on käyty läpi.

## Aika- ja tilavaativuudet sekä suorituskyky

[Billen](https://arxiv.org/pdf/cs/0606116.pdf) (2018) mukaisesti algoritmin pahimman tapauksen tilavaativuus on *O(m)* ja aikavaativuus *O(mn)*, missä *m* on säännöllisen lausekkeen koko, ja *n* luettavan tekstin koko. Aika- ja tilavaativuudet voidaan perustella yleisellä tasolla seuraavasti:

### Tilavaativuus

Lausekkeesta muodostettava NFA koostuu tiloista (State) ja uloskäynneistä (Exit), jotka molemmat ovat luokkaa O(1). Muodostusvaiheessa (luokassa Nfa) jokainen lausekkeen merkki lisää NFA:han korkeintaan kaksi uutta tilaa, ja korkeintaan kaksi uutta uloskäyntiä. Jokainen merkki käydään läpi kerran ja vain kerran. Lisäksi uloskäyntien suhteen meidän täytyy pitää kirjaa vain koko NFA:sta johtavista uloskäynneistä, joten meidän ei tarvitse välittää NFA:n keskellä olevien exitList-listojen oikeellisuudesta, eli voimme tarvittaessa muodostaa uuden uloskäyntilistan suoraan viittauksella vanhaan listaan (esim. List newExits = oldFragment.getExitList()). Näin ollen yhtä uloskäyntiä ei tallenneta moneen kertaan, vaan kokonaisuudessaan muistissa olevien uloskäyntien määrä on verrannollinen lausekkeen pituuteen. Kokonaisuudessaan siis muistissa olevien tilojen ja uloskäyntien määrä on verrannollinen lausekkeen pituuteen ja tältä osin tilavaativuus on luokkaa *O(m)*.

Simulointivaiheessa (luokassa NfaSimulator) simulaattori pitää kirjaa tiloista, joissa automaatti voisi kullakin hetkellä olla. Edelliseen kappaleen mukaisesti automaatissa on korkeintaan *O(m)* tilaa, ja jokaisessa tilassa voidaan olla yhtäaikaisesti korkeintaan kerran (jos algoritmi on jo lisättävässä tilassa suoritushetkellä, tilaa ei lisätä enää uudestaan). Näin ollen myös tältä osalta tilavaativuus on luokkaa *O(m)* tapauksessa, jossa simulaattori on mahdollisesti yhtäaikaisesti kaikissa NFA:n tiloissa.

Tavanomaisen Thompsonin algoritmin toteutuksen lisäksi joudumme pitämään kirjaa indeksistä, josta kuhunkin tämänhetkiseen tilaan on edetty, jotta voimme selvittää että mistä löydetty merkkijono on alkanut. Tämä lisäys vaatii yhden ylimääräisen kokonaisluvun tallentamista jokaisen kokonaisluvun yhteyteen, eli tilavaativuus ei muutu tavalliseen Thompsonin algoritmiin nähden.

Tämänhetkisessä toteutuksessa luettava teksti pidetään suorituksen aikana ohjelman muistissa käytännöllisyyden takia, mutta periaatteessa tälle ei ole algoritmin toiminnan kannalta tarvetta, vaan teksti voitaisiin tarvittaessa lukea vaikka merkki kerrallaan, koska se luetaan aina järjestyksessä eikä peruutuksia tehdä. Jos tekstin pitäminen muistissa halutaan kuitenkin lukea tilavaativuuteen, niin tällöin kokonaisvaativuus on luokkaa *O(m+n)*.

### Aikavaativuus

Syötteen muuttaminen postfix-muotoon on *O(m)* -operaatio. Jokainen syötteen merkki lisätään pinoon ja poistetaan sieltä tasan kerran, ja nämä kummatkin ovat *O(1)* -operaatioita. Lisäksi syötteeseen voidaan lisätä korkeintaan *m* pistettä, mikä sisältyy myäs *O(m)* -vaativuusluokkaan.

Postfix-muotoisen syötteen muuntaminen NFA:ksi koostuu myös pääosin *O(1)* -operaatioista. Ajallisesti haastavin työvaihe yhden merkin lisäämisessä on pahimmassa tapauksessa uuden uloskäyntilistan muodostaminen, ja sekin voidaan tehdä useimmissa tapauksissa vanhan listan pohjalta. Poikkeuksena tästä on |-merkki, jolloin kaksi uloskäyntilistaa pitää liittää yhteen. Tilavaatimusanalyysin mukaisesti uloskäyntien suuruusluokka on *O(m)*, eli pahimmillaan yhden merkin käsittelyn aikavaativuus on luokkaa *O(m)*, ja koko merkkijonon käsitteleminen siis luokkaa *O($m^2$)*.

Simulaatiovaiheessa (NfaSimulator) simulaattoria ajetaan pahimmillaan *n* askelta, kun koko merkkijono käydään läpi. Kunkin askeleen aikana simulaattori voi olla korkeintaan *m* tilassa. Yhden tilan käsittely on pääosin *O(1)* -operaatio, eli yksi merkkien vertailu, kokonaislukujen vertailu (onko tila jo listassa) sekä yhden tilan lisääminen listan loppuun. Poikkeuksen tähän muodostavat NFA:n haarautumat, eli "S-tilat" tässä toteutuksessa. S-tilan lisääminen tarkoittaa sitä, että  haarauman molemmat haarat lisätään tilaluetteloon rekursiivisesti, mikä voi pahimmillaan olla *O(m)* operaatio, jos kaikki tilat joudutaan lisäämään. Simulaattori kuitenkin pitää kirjaa siitä, että onko tila jo lisätty tällä kierroksella luetteloon, joten jos S-tila on jo lisätty luetteloon, ei sitä tällä kierroksella lisätä enää uudestaan. Näin ollen simulaattorin yhdellä askeleella käsiteltävien tilalisäysten määrä on luokkaa *O(m)*, eli kokonaisuudessaan askeleen aikavaativuus on pahimmillaan luokkaa *O(m)*, ja koko simulaation vaativuus luokkaa *O(mn)*.

Aikavaativuuden osalta saadut enimmäisvaativuudet ovat siis *O($m^2$)* ja *O(mn)*. Näistä jälkimmäinen on suurempi on silloin kuin *n > m*, eli luettavan tekstin pituus on suurempi kuin säännöllisen lausekkeen pituus. Tätä voidaan pitää pääosin realistisena oletuksena, joten kokonaisvaativuusluokkaa *O(mn)* voidaan pitää perusteltuna, mutta erikoistapauksessa *n < m* kokonaisvaativuus on *O($m^2$)*


### Suorituskyky

[Testausdokumentin](https://github.com/AaaDee/Regex-Ctrl-F/blob/master/Documentation/Testausdokumentti.md) mukaisesti ohjelmaa testattiin Javan oletustoteutusta vastaan. Yhteenvetona voidaan todeta, että tämä toteus on selvästi (noin 2,5 kertaa) oletustoteutusta hitaampi, ja ero on samaa suuruusluokkaa erilaisilla säännöllisillä lausekkeilla ja eripituisilla teksteillä. Poikkeuksen tekee patologinen erikoistapaus ("(a+a+)+"-alkuinen lauseke), jonka tämä toteutus ratkaisee selvästi oletustoteutusta nopeammin.

## Puutteet ja parannusehdotukset

Ohjelma on toteutettu melko kiireisellä aikataululla, joten parannettavaa luonnollisesti löytyy.

Yksi ilmeinen kehityssuunta on tulkin ymmärtämien toimenpiteiden laajentaminen. Periaatteessa nykyisillä erikoismerkeillä voitaisiin toisintaa valtaosa säännöllisten lausekkeiden yleisesti käytössä olevista erikoismerkeistä, mutta tämä on usein melko työlästä. Lisäksi erillinen pakomerkki ("\" tai vastaava) olisi hyvä toteuttaa.

Ohjelman suorituskyky ei pärjää Javan standarditoteutukselle, joten sekin olisi ilmeinen kehityskohde. Osittain suorituskyvyn puute saattaa johtua tietoisesta valinnasta käyttää usein erillistä luokkaa tilanteissa, joissa primitiivirakenteet tai näiden yhdistelmät olisivat myös toimineet. Näiden luokkien korvaaminen yksinkertaisemmilla rakenteilla voisi nopeuttaa ohjelmaa. Lisäksi ohjelmassa käsitellään merkkijonoja melko huolimattomasti, mikä olisi myös ilmeinen kehityssuunta ottaen huomioon Javan tavan käsitellä String-olioita. Toisaalta ohjelma suoriutuu lähes kaikista perustason hauista melko inhimillisessä ajassa (32 MB:n tekstitiedoston läpikäyntiin kuluu alle 2 sekuntia), joten jos tätä nopeampaa toiminnallisuutta tarvitaan, voi olla perusteltua siirtyä toiseen ohjelmointikieleen tai muuhuun toteutukseen, esimerkiksi useammalla suorittimella yhtäaikaisesti ajettuun grepiin.

Lisäksi ohjelman ulkoasua tulisi päivittää nykyaikaisia standardeja vastaavaksi.

## Lähteet

* [Bille 2018](https://arxiv.org/pdf/cs/0606116.pdf), New Algorithms for Regular Expression Matching. 
* [Cox 2007](https://swtch.com/~rsc/regexp/regexp1.html), Regular Expression Matching Can Be Simple And Fast 
(but is slow in Java, Perl, PHP, Python, Ruby, ...)
* [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html), 2019
* [Thompson 1968](http://doi.acm.org/10.1145/363347.363387), Regular expression search algorithm

