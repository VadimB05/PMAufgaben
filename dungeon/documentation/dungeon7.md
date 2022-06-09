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

Fernkampf:
Projektile sollen zusätzlich zu ihren aktuellen Funktionen auch Gegnern schaden machen, sie zurückstoßen und an Wänden und Feinden anhalten.
Darüber hinaus soll es auch mehrere verschiedene Arten von Projektilen geben.

# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Fernkampf:
Projektile brauchen, ähnlich zu dem Helden und Feinden, eine eigene Hitbox.
Der Code um Projektile an Wänden stehen bleiben zu lassen lässt sich hier komfortabel aus den Monsterklassen übernehmen.

# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Fernkampf:
(10.06.2022, 6 Stunden)
Hitboxen zu Projektilen wurden hinzugefügt, ebenso bekamen sie neue Eigenschaften wie Fluggeschwindigkeit, Reichweite und verursachten Schaden bei Monstern.
Eine weitere Art von Projektil hinzuzufügen stellte sich als recht simpel heraus, da sie lediglich von der abstrakten Projektil Klasse erben musste.


# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Fernkampf:
Die Steine dynamisch während des Spiels zu erzeugen und trotzdem mit der Umwelt zu interagieren bereitete einige Probleme.
Insbesondere die Steine zu "entschärfen" nachdem sie entweder einen Gegner oder eine Wand getroffen hatten um nicht ungewollt noch mehr Schaden zu verursachen.

