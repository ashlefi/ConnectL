
public int search(ArrayList<int> moveScores) {
	int holder = -1;
	
	for(int i = 0; i < moveScores.size; i++)
		if (moveScores[i] > holder)
			holder = moveScores[i];
	
	return holder;
}
