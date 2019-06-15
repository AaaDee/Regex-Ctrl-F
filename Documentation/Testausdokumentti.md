# Testausdokumentti

## Yksikkötestaus

Ohjelman toiminnallisuutta on pyritty testaamaan kattavasti yksikkötestien avulla. Testit ja testausraportti voidaan toisintaa esimerkiksi *gradle jacocoTestReport* -komennon avulla.

Yksikkötestauksessa on keskitytty säännöllisten lauseiden tulkin ja tietorakenteiden testaamiseen, käyttöliittymäluokkia on testattu melko vähän.

## Suorituskykytestaus

### Vertailu Javan oletustoteutukseen

Ohjelman suorituskykyä on verrattu Javan valmiin Regex-toiminnallisuuden suoritusaikoihin seuraavissa esimerkkitapauksissa:

* Yksinkertainen perustapaus, jossa merkkijono "kanada" löydetään 1 MB:n (noin miljoona merkkiä tai 20 sivua) tiedoston lopusta
* Monimutkaisempi tapaus, jossa merkkijonoa "b?i*(ps)+u(m|x)oops" ei löydetä 1 MB:n tiedostosta, mutta se löydetään "osittain" useampaan kertaan
* Patologinen tapaus, jossa merkkijonoa "(a+a+)+kanada" etsitään tiedostosta, jossa on vain 20 a-merkkiä.

Javan oletustoteuksena on käytetty Javan Regex-pakkauksen Pattern- ja Matcher- luokkia, sekä jälkimmäisen matches-metodia. Tapausmerkkijonoihin on lisätty alkuun ".*", jolloin oletustoteutuksen toiminnallisuus vastaa tämän projektin toiminnallisuutta.

Suorituskykytestit löytyvät muitten testien joukosta omasta pakkauksestaan (*PerformanceTest*). Testit ovat melko hitaita, joten ne on oletusarvoisesti poistettu käytöstä @Ignore-komennolla.

Suorituskykytestit ajettiin tavallisella pöytäkoneella (16 GB muistia, i7-7700 CPU @ 3.60GHz × 4 suoritin), ja suoritusajat laskettiin 100 suorituksen keskiarvosta.

Suoritusajat ovat seuraavat:

|                | Ctrl-F | Java Regex |
|----------------|--------|------------|
| Yksinkertainen | 26 ms  | 8 ms       |
| Monimutkainen  | 60 ms  | 24 ms      |
| Patologinen    | 0 ms   | 44 ms      |


Tuloksista nähdään, että tavanomaisissa tapauksissa projektin toteus häviää Javan oletustoteutukselle, ja aikaa kuluu noin 2-3 kertaa enemmän. Toisaalta toteutus kykenee ratkaisemaan patologisen tapauksen selvästi oletustoteutusta nopeammin.

Mahdollisia kehitysideoita suoritusajan nopeuttamiseksi käsitellään [toteutusdokumentin](https://github.com/AaaDee/Regex-Ctrl-F/blob/master/Documentation/Toteutusdokumentti.md) puolella.

### Suoritusajan skaalautuminen tekstin pituuden mukaan

under construction
