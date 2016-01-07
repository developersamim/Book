/**
 * common js function for the pages
 */

function addSubject()
{
	document.getElementById("subName").value= "";
		
	var hiddenInputs = document.getElementsByClassName("hidden");
	for( var i =0; i!= hiddenInputs.length;i++ )
	{
		hiddenInputs[i].style.display = "block";
	}
	return true;
	
	
}