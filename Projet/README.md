Projet P42 : client mobile pour "AddressBook"
=============================================

L'objectif de ce projet est de réaliser un client mobile pour l'API AddressBook développée dans le module W41.

Rendu
-----

- Quand ? **le 28 mars à 24h00**
- Où ? Sur votre dépôt Git, fork de https://git.unistra.fr/p42/p42
- Quoi ?
    - Le code de l'application
    - Un petit [compte-rendu](Compte-Rendu.md) expliquant votre architecture, ce qui est censé fonctionner et ne pas fonctionner, les difficultés que vous avez rencontré.
    

Les tests de votre appli seront réalisés à partir d'une solution fonctionnelle du dernier TP (TP5) de W41. Il est donc important que votre version à vous respecte le cahier des charges des TP de ce module.


Fonctionnalités
---------------

L'application doit proposer un certain nombre de fonctionnalités :

- **Afficher la liste des contacts** : lorsqu'un contact est sélectionné, les informations de ce contact doivent s'afficher, dont la liste des groupes auxquels il appartient.
- **Afficher la liste des groupes** : lorsqu'un groupe est sélectionné, la liste des contacts appartenant à ce groupe doit s'afficher. Un clic sur l'un des contacts peut éventuellement afficher ses détails, mais c'est optionnel.
- **Créer un groupe**
- **Créer un contact**
- **Supprimer un contact** depuis la vue des informations du contact.
- **Supprimer un groupe** depuis la vue des informations du groupe.
- **Ajouter un contact à un groupe** depuis la vue des informations du contact.
- **Supprimer un contact d'un groupe** depuis la vue des informations du groupe.

Bonus : L'application peut permettre d'**associer une photo à un contact**, mais uniquement localement : la photo est stockée sur le téléphone, pas sur le serveur.

Architecture proposée
---------------------

1. L'application est composée d'une activité principale. Cette activité contiendra un [`Bottom Navigation Activity`](https://developer.android.com/studio/projects/templates#BottomNavActivity) qui permettra de naviguer entre les vues. Chaque vue sera affichée dans un `Fragment`.
2. L'écran d'accueil de l'application affiche la vue de la liste des contacts.
3. Les listes de contacts et de groupes sont affichées dans des `RecyclerView`.
4. Quand un contact est sélectionné, les informations du contact sont affichées dans un nouveau fragment.
5. Quand un groupe est sélectionné, les contacts inscrits dans ce groupe sont affichés dans un nouveau fragment.

Pour gérer les clics sur les item d'un `RecyclerView`, vous pouvez vous référer à [ce site](https://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/) ou [celui-ci](https://guides.codepath.com/android/using-the-recyclerview).

Conseils
--------

- Travaillez dans un premier temps avec **des données "en dur"**, sans communication avec l'API
- Concentrez-vous **d'abord sur les contacts** avant d'intégrer les groupes.
- C'est un projet assez conséquent : l'essentiel n'est pas d'implémenter toutes les fonctionnalités mais d'**implémenter "proprement"** celles que vous aurez le temps de faire.
