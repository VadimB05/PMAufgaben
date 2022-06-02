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

Fähigkeiten:
3 Fähigkeiten eine mit Manacost und eine mit Cooldown.
Im laufe des Spieles freischaltbar und benutzbar.
Soll angezeigt werden.

Fernkampf:
Es soll möglich sein Projektile zu verschießen.
Diese sollen bei treffern gegen Feinde stoppen und Schaden machen. An Wänden sollen sie auch anhalten.

# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Fähigkeiten:
Durch 1 Abstrakte Klasse die Methoden vererbt und 3 unter Klassen die Fähigkeiten besitzen.
Die Fähigkeiten werden mit dem Hero Level Freigeschaltet und bei benutzung wird auch angezeigt, dass ein mindest 
Level erreicht werden muss. Zudem kommt noch das Manacosten für 2 Fähigkeiten benötigt werden und eine einen Cooldown besitzt.


Fernkampf:
Eine Abstrakte Klasse Projectile dient als Schablone für die eigentlichen Projektile die verschossen werden können.
Für die Steuerung wurden hier die Pfeiltasten ausgewählt für jeweils alle vier Richtungen in die Geschossen werden kann.

# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Fähigkeiten:
(02.06.2022, 8 Stunden)
5 Klassen erstellt, 3 für die Fähigkeiten 1 für das vererben und 1 für das Array und die Logger Ausgabe.
3 Fähigkeiten wurden Implementiert 2 mit Manakosten und eine mit cooldown, wobei diese noch nicht fertig ist.  

Fernkampf:
Die Klassen wurden erstellt und grundsätzliche Funktionalitäten implemetiert.
Projektile können in alle Richtungen verschossen werden und fliegen nur eine gewisse Distanz.


# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Fähigkeiten:
Die Funktionen des benötigten Hero Level, sowie die Manakosten haben ohne Probleme funktioniert.
Was mir schwierigkeiten gemacht hat ist der Cooldown + Angriff auf ein Monster.
Das 2te ist noch nicht gelöst.

Fernkampf:
Projektile stellten sich als größere Herausforderung dar.
Ich brauchte lange, um dynamisch neue zu erzeugen aber konnte das Problem am Ende lösen.
Nun müssen sie nur noch zusätzlich an Wänden und Monstern anhalten.

