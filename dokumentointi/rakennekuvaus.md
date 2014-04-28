Ohjelma sis‰lt‰‰ yhteens‰ 7 luokkaa: Main, Kayttoliittyma, Tahtitaivas, Pelaaja, Vihollislaivue, Asteroidikentta, AlasPainLiikkuva.

Main-luokka sis‰lt‰‰ main-metodin. Se luo k‰yttˆliittym‰n ja aloittaa t‰htien threadin (jotta t‰hdet liikkuisivat taustalla kivasti ennen kuin peli olisi aloitettu).

Kayttoliittyman ilmentym‰ on pelin k‰yttˆliittym‰, joka k‰ynnist‰‰ pelin ja piirt‰‰ kaiken, mit‰ peliss‰ on.

Tahtitaivas on pelin t‰hdet. Sen ilmentym‰ sis‰lt‰‰ joukon Rectangle-tyyppisi‰ t‰hti‰, joita peli kierr‰tt‰‰.

Pelaaja luokan ilmentym‰ on avaruusalus, jota ohjelman k‰ytt‰j‰ pystyy liikuttamaan. Ohjelman k‰ytt‰j‰ voi myˆs ampua.

Asteroidikentt‰-luokan ilmentym‰ sis‰lt‰‰ joukon Rectangle-tyyppisi‰ asteroideja, joita peli kierr‰tt‰‰.

AlasPainLiikkuva on Tahtitaivas, Vihollislaivue ja Asteroidikentta -luokkien yl‰luokka, joka sis‰lt‰‰ kaikille n‰ille oleellisia metodeja.