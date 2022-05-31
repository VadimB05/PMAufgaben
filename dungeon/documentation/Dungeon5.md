---
title: "Lerntagebuch zur Bearbeitung der Dungeon-Aufgaben Nahkampf und Erfahrung"
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

Magie:
Ein Magiesystem mit verschiedenen (nicht Kampf) Zaubern welche im verlauf des Spiels freigeschaltet werden.

Quests:
Questsystem mit verschiedenen Quests und mit Observern

# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Magie:
Wir brauchen ein System, dass mehrere Zaubersprüche gleichzeitig zur Spielzeit festhalten kann.
Diese Zauber brauchen des weiteren verschiedene Effekte, die sie nützlich für den Spieler machen.
Um im Spielverlauf weitere Zauber freizuschalten, sind sie an die aktuelle Ebene des Dungeons gebunden.
je tiefer man kommt desto mehr Zauber schaltet man frei.

Quests:
Wir benutzen am besten das Type Object Pattern um verschiedene Quests anzulegen.
wir brauchen 2 Klassen, am besten auch noch eine enum Klasse um die verschiedene Quest typen festzulegen.
Außerdem wird eine Observer Klasse und update methode benötigt.

# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Magie:
(19.05.2022, 5 Stunden)
Es gibt nun zwei Zaubersprüche. Einer zum heilen des Helden und einen weiteren um die Laufgeschwindigkeit zu erhöhen.
Die Zauber werden mit erreichen bestimmter Ebenen des Dungeons freigeschaltet.
Sie kosten außerdem Mana beim benutzen.

Quests:
(19.05.2022 - 20.05.2022, 11 Stunden)
Wir haben verschiedene Quests mit verschiedenen Belohnungen erstellt.
Eine Observer Klasse damit die Quests observer sind und bei abschließen der Quest die Quests benachrichtigen
und eine Belohnung bekommen. Alles hat am Ende gut funktioniert, hat bisschen gedauert weil hier und da kleine Fehler
waren.

# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Magie:
funktioniert:
Zauber mit Effekten, Zauber kosten Mana
nicht funktioniert:
Zauber mit steigenden Level freischalten -> gelöst durch binden an das Dungeon Level anstelle des Spieler Levels

Quests:
Observer hat funktioniert, verschiedene Quests anlegen hat auch funktioniert.
Verschiedene Belohnungen haben auch funktioniert und das annehme und ablehnen von Quests
hat am Ende auch funktioniert.
Das die Quests im Spiel an einer bestimmten im Spiel sichtbaren Stelle oder von z.B. einem NPC
annehmbar sind wurde noch nicht implementiert, da die Klassen Hero und Monster erstmal Refactort werden müssen,
damit diese von einer abstrakten Klasse erben und die neue Klasse auch von dieser Klasse erben kann.

