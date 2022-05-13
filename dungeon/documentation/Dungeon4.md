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

Nahkampf:
Ein Nahkampfsystem wobei der angegriffene Character leben verliert und zurückgestoßen wird

Erfahrung:
Ein Erfahrungssystem wobei man Erfahrung sammelt wenn Monster getötet werden
und man bei bestimmter Anzahl an Erfahrung Level aufsteigt.

# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Nahkampf:
Es sollten Kollisions- und Eingabeüberprüfungen erstellt werden.
Alle angegriffenen Charaktäre müssen Zurückfliegen.
Es muss eine Überprüfung erstellt werden, dass man beim Zurückfliegen nicht durch die Wand fliegt.
Die Monster müssen verschwinden sobald diese 0 Leben haben.

Erfahrung:
Die erforderlichen Erfahrungspunkte zum Level aufsteigen müssen exponentiel steigen.
Die Monster müssen Erfahrungspunkte besitzen.
Beim töten von den Monstern muss geschaut werden wie viel Erfharungspunkte mna noch für einen Level aufstieg braucht.


# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Nahkampf:
(12.05.2022 4 Stunden)
MyHero und Monster Klassen mit Schaden und Leben erweitert, MyGame Klasse mit Kollisionsüberprüfungen erweitert.
Funktioniert einwandfrei

Erfahrung:
(13.05.2022 3 Stunden)
MyHero und Monster Klassen mit Erfahrungspunkten und MyHero mit Level erweitert
in MyGame die Erfahrungspunkte von den Monstern exponentiell mit dem levelcounter berechnet
in MyHero die benötigten Erfahrungspunkte exponentiell mit dem Helden Level berechnet
Funktioniert einwandfrei

# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Nahkampf:
funktioniert:
Schaden verteilen, sterben
nicht funktioniert:
zurückfliegen -> gelöst durch abfragen ob das Monster tot ist

Erfahrung:
funktioniert:
Erfahrung bekommen, Level aufsteigen, stärker werden
nicht funktioniert:
exponentielles Steigen der Erfahrungspunkten -> gelöst durch das Setzen von float oder Erhöhen der Erfahrungspunkten
