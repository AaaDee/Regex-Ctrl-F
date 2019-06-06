# Käyttöohje

Helpoin tapa avata ohjelma on suorittaa juuresta löytyvä jar-tiedosto komennolla "java -jar Regex-Ctrl-F.jar".

Esiin avautuu käyttöliittymä. Valitse analysoitava tiedosto "Choose File"-nappulan avulla. Sijannista src/main/resources/samples löytyy Lorem ipsum -teksti koekappaleeksi.

Seuraavaksi syötä etsittävä merkkijono. Perusmerkkien lisäksi käytössä ovat seuraavat erikoismerkit:
* |: tai-merkki, eli a|b on "a tai b"
* *: edeltävä merkki esiintyy 0-n kertaa
* ?: edeltävä merkki esiintyy 0-1 kertaa
* +: edeltävä merkki esiintyy 1-n kertaa
* (): sulkuja voi käyttää syötteen jäsennykseen. Esim. (ab)|(ac) on "ab tai ac"
* .: konkatenaatio, eli a.b "ensin a sitten b". Käytännössä sama kuin pelkkä ab.

Erillistä pakomerkkiä ei ole vielä toteutettu, eli erikoismerkkejä ei voi tällä hetkellä käyttää perusmerkkeinä. Haun kannalta isoilla ja pienillä kirjaimilla ei ole väliä.

Kirjoitettuasi syötteen klikkaa "Run". Ohjelma kertoo, että mistä kohti tiedostoa hakemasi merkkijono löytyy ensimmäisen kerran, tai vaihtoehtoisesti, että merkkijonoa ei löytynyt. Sijainnin numerointi alkaa ykkösestä, eli ensimmäinen merkki on 1 eikä 0. Jos luettavaa tekstiä ei ole rivitetty, niin koko teksti tulkitaan olevan rivillä 1.

