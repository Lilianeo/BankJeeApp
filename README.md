# BankJeeApp
Dans le cadre du tp 4 du développement j2ee, on a crée cette application, qui permet de gérer un ensemble de comptes bancaires

## Le lien du Tp4 [ici](http://miageprojet2.unice.fr/Intranet_de_Michel_Buffa/Cours_composants_distribu%C3%A9s_pour_l'entreprise_%2f%2f_EJB_2013-2014/TP4_EJB_2014-2015_Ajout_de_relations_au_TP3)

## Les fonctionnalités implementées
* Au début nous avons créé un projet de type JEE, et une base de données pour le projet dans laquelle nos tables seront stockées qui sera bien évidemment connectée à notre projet.
Après cela, nous avons créés une entité « CompteBancaire » qui est va contenir toutes les informations nécessaires à la gestion d’un compte bancaire. Comme il est aussi nécessaire de crée un Session bean « GestionnaireDeCompteBancaire » qui va s’occuper de toutes les opérations en relation avec la gestion d’un compte bancaire (création, ajout, …).
Et la partie la plus importante a été l’écriture de la page JSF du backing bean, et cela se fait comme en ajoutant un Template de présentation : un menu en haut et une page principale au centre qui va tenir compte des aspects suivants : 
•	Affichage d'une table contenant tous les comptes bancaires, comme dans le TP1
•	 Possibilité lorsqu'on clique sur un compte d'afficher un formulaire pour ajouter ou retirer de l'argent
•	Possibilité de transférer de l'argent d'un compte à l'autre. Pour le moment on affichera un formulaire dans lequel on entrera les clés primaires du compte source, du compte destination, puis la somme à transférer
•	 Possibilité de supprimer un compte.

Et pour un affichage lisible et ergonomique, l’utilisation du LazyLoading pour l’affichage des tableaux est nécessaire, c’est pour cela que nous avons été amenés à le mettre en place au niveau de ce TP.
Après cela, il a été question d’ajouter de fonctionnalités au backing bean pour une meilleure gestion des comptes bancaire : comme retrait et déport d’argent, ainsi que le transfert d’argent d’un compte à un autre.
 Une fois tout cela terminé, il est question d’enregistrer l’historique de chaque compte (les dépôts et les retrait d’argent) relatifs à chaque compte.
  L’utilisation des composants de Prime Faces est aussi très sollicitée à être utilisé ; ainsi dans ce TP deux composant de Prime Faces ont été utilisés : 
-	Lazy loading : Pour l’affichage paginé des différents tableaux … ainsi quand on a une très grande liste de comptes bancaires, ils vont être affichés dans plusieurs pages à raison de 10 comptes par page.
-	Autocompleting : c’est une fonctionnalité implémentée au niveau du 
On a aussi pris en compte la redirection ainsi quand une personne fait un virement bancaire ou une soustraction d’argent elle va être redirigée vers la page de la liste des comptes pour qu’elle puisse effectuer une nouvelle opération.


## Capture d'ecran
![gestionComptes](https://github.com/marouaneml/BankJeeApp/blob/master/captures/gestionComptes.PNG)
