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

Yllä olevien vertailujen lisäksi testattiin, että miten suoritusaika kasvaa erittäin suurilla teksteillä. Testaustapauksena käytettiin aiemmissa testeissä käytettyä monimutkaiempaa lauseketta, ja tekstin kokoa kaksinkertaistettiin aina 32 MB asti. Jokainen tapaus toistettiin 10 kertaa, ja suoritusajat (millisekunteina) sekä oletustoteutuksen suhteellinen nopeus omaan toteutukseen nähden on esitetty alla.

| Tiedoston koko (MB) | Ctrl-F | Java | Suhteellinen ero |
|---------------------|--------|------|------------------|
| 1                   | 60     | 24   | 43.64%           |
| 2                   | 112    | 46   | 41.07%           |
| 4                   | 224    | 92   | 41.07%           |
| 8                   | 445    | 187  | 42.02%           |
| 16                  | 890    | 372  | 41.80%           |
| 32                  | 1785   | 741  | 41.51%           |

![Suorituskyky eri teksteilllä](/Documentation/images/performanceGraph.png "Suorituskyky eri teksteilllä")

Suoritusajoista nähdään, että tekstin koon kaksinkertaistuessa myös käytetyn ajan määrä suunnilleen kaksinkertaistuu. Testin perusteella vaikuttaa siis melko selvältä, että suurin osa algoritmin ajasta kuluu tällaisella tekstikoolla simulointiin, eli tämä olisi keskeisin vaihe suorituskyvyn optimoimisessa.

Huom: Testaustiedostojen koosta johtuen näitä tiedostoja ei ole GitHub-repositoriossa, joten testi ei ole suoraan toistettavissa GitHubista ladattavassa versiossa. Testi on myös oletusarvoisesti pois käytöstä.
