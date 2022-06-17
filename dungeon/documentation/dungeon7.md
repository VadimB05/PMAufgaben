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

Freundliche NPC:
Es sollen NPCs erstellt werden mit denen man agieren kann und die einen nicht angreifen.

Fähigkeiten:
Cooldown für die Fähigkeiten müssen hinzugefügt werden.

Charakterklassen:
3 unterschiedliche Charakterklassen mit einem verschiedenem Wert.
Sollen unterschiedliche Bezeichungen haben
Mit einem Pattern
Eine Auswahl für die Charaktere

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

Freundliche NPC:
Es muss ne neue Klasse erstellt werden, welche von Character erbt.

Fähigkeiten:
Ein int Wert in endframe der hochgezählt wird und mit dem man abfragen kann wie oft hochgezählt wurde.

Charakterklassen:
2 Klassen, eine für das Visuelle mit der Auswahl und eine Klasse mit Enum und Type object Pattern für die Klassen.

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

Freundliche NPC:
(02.06.2022, 3 Stunden)
Beim refactoren und für die Quests wurden NPC schon erstellt somit mussten wir nichts extra machen.

Fähigkeiten:
(09.06.2022, 3 Stunden)
Es wurde ein Integer Wert erstellt mit dem ein Counter für die Fähigkeiten implementiert wird.

Charakterklassen:
(10.06.2022 5 Stunden)
Eine Klasse mit einem Enum und Type object Pattern.
Das Spiel wird zu Anfang Pausiert und wird auf Knopf druck freigeschaltet. Auch Monster können sich nicht bewegen.

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

Freundliche NPC:
Alles hat gut funktioniert, die Aufgabe wurde erledigt ohne zu wissen dass es die Aufgabe gibt.

Fähigkeiten:
Es hat direkt funktioniert, es war eine eher leichte Aufgabe.

Charakterklassen:
Das erstellen der Klassen hat keine Probleme bereitet aber das Visuelle auswählen schon und ist auch noch nicht fertig.