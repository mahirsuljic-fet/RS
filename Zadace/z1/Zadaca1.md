# Problem 1

### 1.1
***Pitanje***:
```
Da li će se kompajliranje i izvršavanje naredne klase uspješno obaviti? Dati detaljno
obrazloženje odgovora.
```
``` java
public class Assignment {
    public static void main(String[] args) {
        int a, b, c;
        b = 10;
        a = b = c = 20;
        System.out.println(a);
    }
}
```

***Odgovor:*** \
Kod ce se kompajlirati, vrijednosti varijabli `a`, `b` i `c` ce biti `20` jer je operator `=` desno asocijativan i vraca vrijednost koju dodijeli.
Sve su inicijalizirane i u klasi `Assignment` se nalazi method `public static void main(String[] args)` koji predstavlja entry point programa.


### 1.2
***Pitanje***:
```
Šta će biti ispisano nakon izvršenja sljedećeg segmenta Java programa? Dati detaljno
obrazloženje odgovora.
```
``` java
int[] niz = { 4, 8, 16 };
int i = 1;
niz[++i] = --i;
System.out.println(niz[0] + niz[1] + niz[2]);
```

***Odgovor***: \
Prvo se izvrsava `++i` unutar operatora `[]` na nizu, sto varijablu `i` postavi na vrijednost `2`.
Zatim se izvrsava `--i` sto postavlja variablu `i` na vrijednost `1` i vraca tu vrijednosti.
Nakon ovoga efektivo se dobija `niz[2] = 1`, pa izraz `niz[0] + niz[1] + niz[2]` ce biti `4 + 8 + 1` sto daje rezultat `13` sto se ispise u terminalu kada se kod izvrsi.


### 1.3
***Pitanje***:
```
Šta će biti rezultat kompajliranja sljedećeg programa?
```
``` java
public class MyClass {
    long var;

    public void MyClass(long param) {
        var = param; 
    } // (1)

    public static void main(String[] args) {
        MyClass a, b;
        a = new MyClass();  // (2)
        b = new MyClass(5); // (3)
    }
}
```
```
a) kompajler će javiti grešku na (1), jer konstruktor ne može specificirati povratnu
vrijednost..
b) kompajler će javiti grešku na (2), jer klasa nema podrazumjevani konstruktor.
c) kompajler će javiti grešku na (3), jer klasa nema konstruktor koji uzima jedan
argument tipa int.
d) Program će se uspješno kompajlirati.
```

***Odgovor***:
```
c) kompajler će javiti grešku na (3), jer klasa nema konstruktor koji uzima jedan argument tipa int
```

Dio koda:
``` java
public void MyClass(long param) {
  var = param;
}
```
je zapravo definicija metoda koji se zove `MyClass`, a ne konstruktora.
Zbog toga kompajler nece javiti gresku na `(1)`.

Konstruktori ne smiju imati povratni tip, pa cak ni `void`.
Dakle, klasa nema ni jedan korisnicki definisan konstruktor, pa time ima default konstuktor i samo default konstruktor.
Zbog toga kompajler nece javiti gresku na `(2)`.

Kompajler javlja gresku na `(3)` jer nema konstruktora koji prima jedan `int`.
Ako bi definisali konstuktor kao:
``` java
public MyClass(long param) {
  var = param;
}
```
onda kompajler ne bi javio gresku na `(3)` zato jer se moze izvrsiti autmatska promocija iz `int` u `long`, ali bi u tom slucaju javio gresku na `(2)` jer definisanjem ovog konstruktora se *brise* default konstruktor.


### 1.4
***Pitanje***:
```
Koji iskaz je tačan u pogledu pristupa članovima?
    a. Privatnim članovima se uvijek može pristupiti unutar istog paketa
    b. Privatnim članovima se može pristupiti iz koda klase kojoj pripadaju.
    c. Članu sa podrazumjevanim pristupom može se pristupiti iz svake podklase klase
    u kojoj je definisan.
    d. Privatnim članovima se uopšte ne može pristupiti.
```

***Odgovor***: \
Tacan odgovor je pod `b.`.

`a.` je pogresno jer se privatnim clanovima moze pristupati samo unutar iste *klase*.

`c.` je pogresno jer se clanovima sa default pristupom moze pistupiti samo unutar istod paketa. Ako je podklasa u drugom paketu, nece moci pristupiti tom clanu.

`d.` je pogresno jer se privatnim clanovima moze pristupati unutar klase u kojoj su definisani.


### 1.5
***Pitanje***:
```
Šta se dešava tokom izvršenja sljedećeg programa?
```
``` java
int i = 0;
int[] a = { 3, 6 };
a[i] = i = 9;
System.out.println(i + " " + a[0] + " " + a[1]);
```
```
    a) Ispisuje "9 9 6"
    b) Ispisuje "9 0 6"
    c) Ispisuje "9 3 6"
    d) Ispisuje "9 3 9"
```

***Odgovor***: \
Tacan odgovor je `a)`.

Od interesa je linija `a[i] = i = 9;`.
Prvo ce se `a[i]` zamijeniti sa `a[0]`, 
jer operator `[]` ima vrci prioritet od operatora `=`.
Zatim se izvrsava operator `=`.
Operator `=` je desno asocijativan, pa se prvo izvrsava `i = 9` sto vraca `9`.
Sada `i` ima vrijednost `9`, a vracena vrijednost `9` se sprema u `a[0]`.
Time se dobija ispis `9 9 6`.


### 1.6
https://github.com/mahirsuljic-fet/RS/blob/b4b5b6b8781c7a8f31283ffaf64c000ca7320b7c/Zadace/z1/z1_6.java#L1-L149

# Problem 2
https://github.com/mahirsuljic-fet/RS/blob/9a4a27577c4c7ceb18e8e7919171672636c4a387/Zadace/z1/z2.java#L1-L146
