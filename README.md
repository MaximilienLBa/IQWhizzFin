# LSINF1225-2019-GoupeJ1B.2-IQTest

Bonjour, 

vous trouverez dans ce git toute les classes java et xml à la bonne utilisation de notre code. Pendant tout le long du projet,
l'émulateur utilisé à été un Pixel 2 d'API 27 tournant sous android OREO x86 avec google 8.1 comme target.

En ce qui concerne le contenu de ce git, vous trouverez déjà l'APK de l'application (IQWhizz-j1B.apk) mais aussi le rapport en pdf
(Rapport 3-Developpement - Android.pdf) ayant toute les informations demandé dans les consignes pour la construction du rapport.

Maintenant pour le code en lui même vous trouverez les classes java en allant dans le dossier "app" ensuite "src" puis "main"
de là si vous allez dans le fichier "iqwhizz" vous y retrouverer toute les classes java :

- dossier database : - Quiz contract = initialisation des tables et de leurs colonnes
                     - QuizDbHelper = contient la création BDD ainsi que toute les méthode l'utilisant
 
- catégory : classe d'initialisation des catégories permettant aussi la création de nouvelle pour l'avenir
- classement : comme son nom l'indique, c'est la classe qui permet d'avoir le classement mais le top 10
- classementAll : comme son nom l'indique, c'est la classe qui permet d'avoir le classement totale de l'application
- DuelActivty : classe qui permet que quand tu cliques sur un ami dans la list d'ami tu puisses lancer les quiz avec lui
- FriendActivity : classe qui permet d'ajouter, de supprimer des amis et d'acceder à la liste
- FriendList : Liste d'ami
- Profile Activiy : classe qui permet l'affichage des données et leurs modifications dans la BDD
- Question: comme pour catégory, c'est une classe pour inialisé les questions
- QuestionReviewActivity: comme son nom l'indique, c'est une classe pour avoir une review des bonnes réponses et de celle que 
                          l'utilisateur à mis
- QuizActiviy : classe qui permet le lancement de quiz, le calcul du score + chronomètres 
- RegisterActivity: classe qui permet de s'enregister dans l'application et donc la BDD
- ReviewActivityFriend : classe qui permet d'avoir l'affichage des scores de l'ami et l'utilisateur connecté
- StartingScreenActivity : classe qui permet l'affichage et la redirection dans les différentes activité de l'application
- User : comme pour question et catégory, classe d'initialisation d'un utilisateur

Ensuite en revenant en arrière au niveau du "main" ou l'on peut aller dans le fichier "res" puis "layout", ce dossier contient toutes les
classes XML utile à l'affichage de notre application. Il y a aussi le dossier "drawable" dans "res" où l'on peut trouver toutes les images
utilisé
