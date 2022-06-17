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

Charakterklassen:
3 unterschiedliche Charakterklassen mit einem verschiedenem Wert.
Sollen unterschiedliche Bezeichungen haben
Mit einem Pattern
Eine Auswahl für die Charaktere

Fähigkeiten:
Cooldown für die Fähigkeiten und Schaden müssen hinzugefügt werden.


Shop:
Shop aus dem man was kaufen kann
bei dem man items verkaufen kann
bei dem man mit einer Währung bezahlen kann
bei dem Shop soll es über regexp laufen


# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Charakterklassen:
2 Klassen, eine für das Visuelle mit der Auswahl und eine Klasse mit Enum und Type object Pattern für die Klassen.

Fähigkeiten:
Ein int Wert in endframe der hochgezählt wird und mit dem man abfragen kann wie oft hochgezählt wurde.
Den Schaden als int Wert in Blackhole gesetzt für den Kampf gegen Monster.

Shop:
Eine Charakterklasse sollte erstellt werden, die als NPC dient
Der NPC soll Items besitzen
eine Grafik muss gezeichnet werden
die Items dürfen nur begrenzt vorhanden sein
Die Items müssen anziehbar sein


# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Charakterklassen:
(16.06.2022 4 Stunden)
Durch das Drücken der Tasten 7,8,9 kann man die zugeordnete Klasse wählen.

Fähigkeiten:
(16.06.2022 2 Stunden)
Schaden und Cooldown wurden der fehlenden Fähigkeit Blackhole hinzugefügt.

Shop:
(16-17.06.2022, 12 Stunden)
Ein NPC wurde erstellt, der eine Grafik anzeigen lässt
Beim Shop NPC kann man begrenzte Sachen mit Knochen kaufen
Die Items werden vom Shop nach dem Kauf fallengelassen
Die Items kann man anziehen/verwenden


# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Charakterklassen:
Ergänzung für die Auswahl jetzt auch im Spiel möglich, ohne den Code zu verändern und ein Bild für die Grafische Darstellung hinzugefügt.

Fähigkeiten:
Hat funktioniert ohne große Probleme.

Shop:
Regexp hat garnicht funktioniert, keine Eingabe wollte außer die Abfrage
für die einzelnen Tasten, jedoch kann ich nicht 80 if Abfragen erstellen.
Der Scanner hat nicht funktioniert, der TextInputListener hat genauso
wenig funktioniert.
Es gab einen Fehler nach dem mergen, was ich zu spät bemerkt hatte, der mir zu
viel Zeit kostete, aber nach dem ich den bemerkte ging es auch schon wieder
gut voran.
