// global variable to store array of triggers
var triggers;
var old_words = "";

function getTriggers()
{
	block(); // do SimpleModal
	
	$.ajax({
		  url: 'TriggerServlet',
		  dataType: 'json',
		  success: getTriggerCallback,
		  error: getTriggerError
		});	
}

function getTriggerCallback(data)
{
	triggers = data;
}

function getTriggerError(request,status,error)
{
	alert("No triggers were found!");
}

function checkTriggers(words) {
	if (words) {
		//Check only one word at a time
		var word = words.replace(old_words,"");
		var word_trimmed = trim_whitespace(word);
		
		var trigger_answer = triggers[word_trimmed.toLowerCase()];
		if (trigger_answer !== undefined) {
			startAnswer();
			$('#answerBox').val(trigger_answer);
			submitAnswer();
		}
		
		old_words = words;
	}
}

function trim_whitespace (str) {
    return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}