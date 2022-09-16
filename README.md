# XoX-client-server-app :sunglasses:

XoX igrica izmedju dva klijenta koja su povezana na server.
Komunikacija izmedju klijenta i servera je implementirana preko TCP(socket) protokola koriscenjem Java API-ja.

Igrica se odvija u konzoli(terminalu).

Prvi potez unosi igrac koji se prvi poveze na server tako sto unosi jedan broj od 0-8(redosled polja na xox matrici).

Zatim igra drugi igrac,i tako naizmenicno.

Pokretanje:
Preuzeti repo i pokrenuti ga u InteliJ razvojnom okruzenju, konfigurisati JDK-17, zatim build-ovati, i run-ovati aplikaciju.

**TODO:**
- Implementirati kraj igrice tj.analizu matrice da li je neki od igraca popunio odgovarajuca polja.
- Obavestiti klijent koji je igrac pobedio.
