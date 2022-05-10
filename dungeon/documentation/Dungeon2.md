---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben HUD"
author:
-   "Oskar Schaubert (oskar.schaubert@fh-bielefeld.de)"
-   "Vadim Balysev (vadim.balysev@fh-bielefeld.de)"
-   "Abdurakhman Vaysert (abdurakhman.vaysert@fh-bielefeld.de)"
hidden: true
---

<!--
Führen Sie zu jeder Woche zur Bearbeitung der Dungeon-Aufhaben ein
Lerntagebuch in Ihrem Team. Kopieren Sie dazu diese Vorlage und füllen
Sie den Kopf entsprechend aus.

Im Lerntagebuch sollen Sie Ihr Vorgehen bei der Bearbeitung der jeweiligen
Dungeon-Aufgaben vom ersten Schritt bis zur Abgabe der Lösung dokumentieren,
d.h. wie sind Sie die gestellte Aufgabe angegangen (und warum), was war
Ihr Plan und auf welche Probleme sind Sie bei der Umsetzung gestoßen und
wie haben Sie diese Probleme gelöst. Beachten Sie die vorgegebene Struktur.

Für jede Abgabe sollte ungefähr eine DIN-A4-Seite Text erstellt werden,
d.h. ca. 400 Wörter umfassen. Wer das Lerntagebuch nur ungenügend führt
oder es gar nicht mit abgibt, bekommt für die betreffende Abgabe 0 Punkte.

Checken Sie das Lerntagebuch mit in Ihr Projekt/Git-Repo ein.

Schreiben Sie den Text mit [Markdown](https://pandoc.org/MANUAL.html#pandocs-markdown).
Tipp: VSCode bringt einen vergleichsweise guten Markdown-Support (inkl. Preview)
bereits in der Grundinstallation mit.

Geben Sie das Lerntagebuch stets mit ab. Achtung: Wenn Sie Abbildungen
einbetten (etwa UML-Diagramme), denken Sie daran, diese auch abzugeben!
-->


# Aufgabe

<!--
Bitte hier die zu lösende Aufgabe kurz in eigenen Worten beschreiben.
-->

HUD:
Das HUD soll mithilfe des Quick-Start Guides implementiert und für Zukünftige Aufgaben auf den aktuellsten Stand gehalten werden.

Items:
Es sollen Items mit der Veerbungshirarchie erstellt werden. Über Generiks werden diese dann ins Inventar gepackt.

Inventar: Es soll ein Inventar erstellt werden, welches mit dem HUD zusammen arbeitet und die Items aus dem Inventar beim HUD ausgibt.


Monster:
Es sollen mehrere verschiedenene Monster mit eigenen Animationen und Eigenschaften hinzugefügt werden.
Diese laufen zufällig durch den Dungeon und sollen mit steigenden Level mehrzähliger und stärker werden.


# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

HUD:
implementierung des HUDs nach dem Guide

Items:
- Die Klassen sollte mit Generics arbeiten.
  Es gibt für alle Item Arten einen Ordner, wo dann auch die  Abstrakten Klassen vererben an die Items.
  Es existiert auch eine Oberklasse Items, die von Entity erbt.
  Damit Code nicht zu oft doppelte vorkommt, war das sehr hilfreich und hat uns arbeit abgenommen.

Inventar:
Inventar ist eine Liste von Items
die Liste soll mit Generiks arbeiten


Monster:
Monster teilen viele Eigenschaften mit der Spielerfigur. Sie werden ebenfalls zufällig im Dungeon plaziert und können dort herumlaufen. 
Dies legte die Vermutung nahe, dass die Monster Klassen auch vom Code her ähnlich zum Helden gestaltet sind. 
Der bereits vorhandene Code des Helden lässt sich also als inspiration und vergleich nehmen bei dem implementieren. 


# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

HUD:
HUD am 28.04.2022 implementiert (60 Minuten)

Items:
28.04.2022 implementiert (6 Stunden)
Wir haben eine Übersichtliche Ordner Struktur aufgebaut und eine einfache Struktur die man schnell verstehen kann erschaffen.
Die Items werden Random generiert im dungeon und man kann mit ihnen interagieren. Aufheben und im Inventar anzeigen, wobei man gewisse gegenstände austauscht, wenn der Platz schon belegt ist
Die Items haben auch eigene Werte, wie Verteidigung, Angriff und Geschwindigkeit z.B.


Inventar:
28.04.2022 - 29.04.2022 (11 Stunden) 
Wir haben eine Liste erstellt die nur Objekte vom Typ Items aufbewahren kann.
Anschließend haben wir die Liste so bearbeitet, dass Items hinzugefügt und herausgenommen werden können.
Die herausgenommenen Items mussten wieder auf dem Boden angezeigt werden.
Alle Items sollten per Tastendruck in der Konsole angezeigt werden.



Monster:
Monster am 28.04.2022 teilimplementiert (8 Stunden)
Wie erwähnt, wurde der vorhandene Code der MyHero Klasse als Vorlage genommen.
Eine Monsterklasse wurde angelegt welche von der Animatable Klasse erbt und die nötigen Methoden wurden sehr Grundlegend implementiert.
Das erst Ergebnis war ein Monster, dass an der gleichen Stelle wie der Spieler Charakter erscheint und dort stehen bleibt. Die Animation wurde ebenfalls richtig abgespielt.
Der Code war an dieser Stelle noch recht unsauber und uns wurde bewusst, dass wenn wir weitere Monster hinzufügen es zu viel redundanten Code kommen würde.
Die Struktur des Codes wurde deswegen nochmals ausgearbeitet.
Es wurde ein neues Package für nicht Spieler Charaktere angelegt, in diesem liegt eine Abstrakte Oberklasse von welcher andere Charaktere erben und eigene Packages für diese Charaktere.
Ein großteil des Codes für das Monster wurde in die Klasse Charakter verlagert.
Am Ende musste in die eigentliche Monster Klasse nur die Animationen geladen und später noch die Laufgeschwindigkeit hinzugefügt werden.
Dies machte das erstellen einer weiteren Monster Klasse viel schneller und leichter.
So hatten wir nun die Monster Chort und Imp, welche am Anfang des Spiels auf zufälligen Positionen auf der Karte erschienen.
Diese sich zufällig bewegen zu lassen stellte sich als große Herausforderung dar.
Wir brauchten ziemlich lange um eine angemessene Lösung zu finden, doch kamen vor allem durch Abdurakhmans Arbeit zu einen funktionierenden Algorithmus.
In der Methode randomMovement() wird eine zufällige Zahl zwischen 0 und 4 generiert welche bestimmt, ob das Monster sich in eine der 4 Himmelsrichtungen bewegt oder stehen bleibt.
Wurde eine Entscheidung getroffen, wird diese Aktion für 30 Frames des Spiels ausgeführt und nach dem nächsten Input gewürfelt.
Dazwischen wird in der update() Methode immer wieder kontrolliert, ob der Weg des Monsters auch begehbar ist.
So konnten beide Monster sich durch den Dungeon bewegen. Um diese nun noch mehr von dem Spielercharakter zu unterscheiden, laufen die Monster mit einer anderen Laufgeschwindigkeit vom Helden.
Der Chort bewegt sich etwas langsamer als der Spieler, während der Imp sehr viel schneller ist.



# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

HUD:
die Implementation lief reibungslos

Items:
Items war im großen und ganzen nicht all zu Schwer im nachhinein zu implimentieren aber es hat schon viel Zeit gekostet sich Gedanken darüber zu machen, wie man das ganze genau angeht.

Inventar:
Für das Inventar hat alles funktioniert, nur hat es eine längere Zeit in Anspruch genommen als gewünscht und erwartet.
Kleine Probleme die beim Kopieren enstanden sind und ettlich Minuten gekostet haben.
Manche Probleme durch Zufall bemerkt, andere durch die Fehlerausgabe. Debugger und Testfälle haben uns auch gut geholfen.


Monster:
Wir konnten herausfinden, dass das frühe Umlagern und Zusammenfassen von Code enorm im späteren Verlauf hilft und das Implementieren weiterer Features erleichtert.
Die Monster zufällig laufen zu lassen stellte sich als große Herausforderung dar, die den großteil der Arbeitszeit beansprucht hatte.
Wir kamen aber Schrittweise an die Lösung dran, mit immer richtiger werdenden Ansätzen was letztenendlich auch Spaß gemacht hatte.