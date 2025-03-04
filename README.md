# Icefield
## Compiling and running the game
Compile using:
```
javac $(find ./src/* | grep .java) -d bin
```
Run the game with this command:
```
java -cp bin main.Main
```

## Keybindings
Arrows: movement

A: assemble the spare gun

D: dig

T: tent

E: eat

Space: end turn

G: collect object

S: use special ability
- - - -

## Jégmező

A játékban a különböző képességű szereplőknek (3 vagy több játékos lehet) kell egy tengerrel körülvett jégmezőn túlélniük. A szereplők lehetnek eszkimók vagy sarkkutatók, és körökre osztva tevékenykednek.

A jégmező jégtáblákból áll. Vannak stabil jégtáblák, amelyeken akárhány szereplő állhat, és vannak instabil jégtáblák, amik adott létszám felett átfordulnak és ilyenkor a rajtuk állók a vízbe esnek. A jégtáblákat a játék kezdetén eltérő mennyiségű hó borítja.

Az egyes jégtáblákba különféle tárgyak lehetnek belefagyva: lapát, kötél, búvárruha, élelem, stb. Befagyott tárgyat csak akkor lehet meglátni és kiásni, ha a jégtábla tiszta, nem borítja hó. A jégtáblák között lehetnek hóval fedett lukak is, amibe beleesve csak a búvárruhát viselők élnek túl, vagy azok, akiket egy köteles barátjuk a szomszéd jégtábláról azonnal kimenekít.

Minden szereplő egy körben 4 egységnyi munkát végezhet. Ilyen munka például a jégtáblán levő egységnyi mennyiségű hó eltakarítása, egy szomszédos jégtáblára való lépés vagy egy kiásott tárgy felvétele. A lapáttal két egységnyi hó takarítható el egy munkaráfordítással.

A jégmezőn időnként feltámad a hóvihar, és néhány érintett jégtáblát újabb adag friss hóval borít be. Akit elkap, annak a testhője egységnyivel csökken. Az eszkimóknak a játék elején 5 egység testhője van, a sarkkutatónak csak 4. Egy élelem eggyel növeli a testhőt.

A szereplők jégtábláról-jégtáblára haladnak képességeiknek megfelelően. A sarkkutató meg tudja nézni, hogy az a jégtábla, amire lépne, hány embert bír el (a luk egyet sem). Az eszkimó tud iglut építeni, amiben átvészelhetők a hóviharok. Egy-egy képesség alkalmazása is egy-egy munkát jelent.

A játék célja egy jelzőrakéta alkatrészeinek (pisztoly, jelzőfény, patron) megtalálása. Az alkatrészek is a jégbe vannak fagyva. Ha ezeket a csapat összegyűjti és ugyanarra a jégtáblára viszi, akkor egy munka felhasználásával összeszerelhetik és elsüthetik, amivel megnyerik a játékot. Ehhez azonban mindannyiuknak ugyanott kell állniuk. Ha valaki menet közben meghal (vízbe esve megfullad vagy elfogy a testhője és kihűl), akkor a játék véget ér.


Módosítás

A feladatleírás az alábbiakkal bővül: 
1) új kiásható tárgy: sátor (használatkor úgy viselkedik, mint az iglu, de egy kör után eltűnik)
2) új kiásható tárgy: törékeny ásó (2 havat tud lapátolni, de 3 használat után eltörik és onnantól nem működik)
3) új szereplő: jegesmedve. Véletlenszerűen kószál, körönként egyet lép. Ha elkap valakit (közös jégtáblán áll vele), vége a játéknak. Az iglun átgyalogol, nem megy be (az iglu megvédi a bentlevőket), de sátorba be tud menni, az nem véd ellene.

A modellt érintő módosulásokat a prototípus koncepciója c. dokumentum 0. fejezetében kell bemutatni (7.0.1: osztálydiagram, 7.0.2: új osztályok leírása, meglevő osztályok esetleges módosulása, 7.0.3: az új elemek miatt előálló új vagy módosult szekvencia-diagramok).

A szkeleton fázisban leadott dokumentumokba a változást nem kell átvezetni. A változásoknak csak a továbbiakban leadandó dokumentumokban kell megjelenniük.

### Screenshots:
![](assets/screenshot1.png)
![](assets/screenshot2.png)
![](assets/screenshot3.png)
