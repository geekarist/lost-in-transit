Calcul de score

	En tant que voyageur
	Je veux évaluer si une adresse est bien desservie par les transports 
	Afin d'acheter un appartement

Saisie d'adresse

	Quand j'accède à la page principale de l'application
	Alors je peux saisir une adresse

Evaluer l'adresse

	Etant donné que j'ai saisi une adresse
	Quand je clique sur le bouton "évaluer"
	Alors je vois le score correspondant à cette adresse

Comparer 2 scores 

	Etant donné que j'ai évalué une adresse bien desservie (18 place de la Nation, Paris)
	Et que j'ai saisi une adresse mal desservie (76 rue Haxo, Paris)
	Quand je clique sur "évaluer"
	Alors je vois le score des 2 adresses
	Et le score de l'adresse bien desservie est le meilleur des 2
