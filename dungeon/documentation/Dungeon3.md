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

Fallen: 
2 Fallen Implementiert. Zum einen Stacheln die eine Animation haben und ein Loch.

GameOver:


Monster:
Die Monster müssen in zufälliger Zahl generiert werden und mit jedem level vielzähliger.


# Ansatz und Modellierung

<!--
Bitte hier den Lösungsansatz kurz beschreiben:
-   Wie sollte die Aufgabe gelöst werden?
-   Welche Techniken wollten Sie einsetzen?
-   Wie sah Ihre Modellierung aus (UML-Diagramm)?
-   Worauf müssen Sie konkret achten?
-->

Fallen:
Es soll eine Abstrakte Klasse geben für die Traps, damit die Funktionen nicht mehrmals vorkommen.
1 Hole Klasse für die eine Trap. 1 Spikes Klasse für die andere Trap.
Man muss drauf achten das man den Damage Wert z.B. nicht in der Trap Klasse bestimmt, sondern
in den eigenen individuellen Trap Klassen, damit nicht alle den selben Wert besitzen.

GameOver:


Monster:
Ich guckte über alle stellen in der myGame Klasse an welcher Monster erzeugt und in das Level gesetzt werden.
Ich hatte eine ungefäre Ahnung, dass die zufällige Generierung an diesen Stellen stattfinden muss.
Durch zusammenfassen dieser Codestellen in eigenständige logische Abschnitte, wurde mir das finden der eigentlichen Lösung vereinfacht.

# Umsetzung

<!--
Bitte hier die Umsetzung der Lösung kurz beschreiben:
-   Was haben Sie gemacht,
-   an welchem Datum haben sie es gemacht,
-   wie lange hat es gedauert,
-   was war das Ergebnis?
-->

Fallen:
03.05.2022 implementiert(4 Stunden)
3 Klassen wobei 2 Fallen von der Abstrakten Klasse "Traps" erben.
Wenn der Held auf die Falle tritt, kommt es zur kollision und er erleidet Schaden.

GameOver:

Monster:
05.05.2022 weiter implementiert(3 Stunden)
Die meiste Arbeit wurde bereits im vorherigen Tagebucheintrag erwähnt.
Das generieren einer zufälligen Anzahl von Objekten erzeugte einige Probleme.
Größtenteils fiel es mir schwer nachzuvollziehen, wie genau man auf spezifische instanzen von zufällig generierten Objekten zugreifen konnte.
Die Lösung fand sich auf StackOverflow, man läd die Objekte bereits beim erzeugen in eine Liste und kann so auf einzelne Elemente der Liste zugreifen.
Um die Monster in späteren Level vielzähliger zu machen, addierte ich die zufällige Zahl zu der Zahl des aktuellen Levels.



# Postmortem

<!--
Bitte blicken Sie auf die Aufgabe, Ihren Lösungsansatz und die Umsetzung
kritisch zurück:
-   Was hat funktioniert, was nicht? Würden Sie noch einmal so vorgehen?
-   Welche Probleme sind bei der Umsetzung Ihres Lösungsansatzes aufgetreten?
-   Wie haben Sie die Probleme letztlich gelöst?
-->

Fallen:
Anfang hat es Probleme bei der Animation gegeben aber dies wurde schnell gelöst. Der Held wurde unter der Falle animiert, was nicht richtig war.
Wir haben den Helden ganz nach unten bei den entityController.add(hero); gepackt, so der er immer an erster stelle steht über allem.

GameOver:


Monster:
Die Die Aufgabe hatte mir zwar schwierigkeiten bereitet, durch ihr lösen konnte ich jedoch einiges für die Zukunft lernen.
Erst relevante Codeabschnitte zusammenzufassen stellte sich ebenfalls als gute Technik heraus.