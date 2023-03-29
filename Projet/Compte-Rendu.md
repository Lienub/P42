## Compte Rendu Projet P42
Julien ABID<br>
Tristan DURIEUX

### Architecture

Nous avons choisi d'utiliser une architecture MVVM (Model-View-ViewModel) pour notre application.

L'architecture MVVM nous a permis d'utiliser et d'exploiter les LiveData, nécessaire au bon fonctionnement de l'application.
### Fonctionnalités
Parmi la liste de fonctionnalités adressées, nous avons les avons toutes implémenté sauf  : *Supprimer un contact d'un groupe depuis la vue des informations du groupe.* 

Parmi toutes les fonctionnalités implémentées, elles font toutes ce qu'on leur demande.

Elles sont donc toutes sensées fonctionner correctement, sauf la création d'un contact ou d'un groupe. En effet, lors de la création d'un contact ou d'un groupe, le RecyclerView concerné ne se met pas à jour automatiquement. Le contact ou le groupe est bien ajoutée à la base, mais il faut donc quitter l'application et la relancer pour voir le contact ou le groupe apparaître dans la liste.

#### Bonus
De plus nous avons ajoutés des fonctionnalités supplémentaires comme bonus :
- Ajout d'une adresse mail pour un contact
- Ajout d'une adresse postale pour un contact
- Ajout d'un numéro de téléphone pour un contact

Ces trois fonctionnalités sont accessibles depuis la vue des informations d'un contact.

Elles fonctionnent parfaitement, mis à part le fait que les données enregistrées peuvent mettre un peu de temps à s'afficher dans la vue, voir la ou les données ajoutées ne s'affichent pas.

### Difficutés rencontrées

La première difficulté rencontrée a été de comprendre comment fonctionner et implémenter le RecyclerView et son environnement. Nous avons donc dû lire la doc et regarder des tutoriels sur internet. (Youtube,  StackOverflow, etc.)
Mais aussi en demandant à d'autres étudiants de la promotion.

La deuxième difficulté rencontrée a été de comprendre comment implémenter les LiveData. Nous avons donc dû lire le cour, la doc et regarder des tutoriels sur internet. (OpenClassroom, etc.)

Ce qui a aussi été compliqué, c'était de se familiariser à l'IDE Android Studio. C'est un IDE gourmand en ressources qui a tendance à ramer voir à planter. Sans une bonne configuration d'ordinateur cela pouvait se compliquer au moindre lancé de l'application.

Pour finir nous avons eu des difficultés au niveau du timing, car nous avons pas mal d'autre projet en parallèle + les partiels , donc c'est un gros projet sur lequel nous pensons ne pas avoir assez eu beaucoup de temps par rapport à ce qui était demandé.     

Nous avons donc du faire des choix et nous avons donc décidé de ne pas implémenter toutes les fonctionnalités demandées.