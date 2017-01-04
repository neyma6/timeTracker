<div class="adminBanner">
<p>TIME TRACKER ADMIN</p>
</div>

<div id="adminHeader">
<ul>
	<li><a class="${param.activeItem == 'activityAdd' ? 'active' : ''}" href="addActivity">Add Activity</a></li>
	<li><a class="${param.activeItem == 'activityDel' ? 'active' : ''}" href="deleteActivity">Delete Activity</a></li>
	<li><a class="${param.activeItem == 'personAdd' ? 'active' : ''}" href="addPerson">Add Person</a></li>
	<li><a class="${param.activeItem == 'personDel' ? 'active' : ''}" href="deletePerson">Delete Person</a></li>
</ul> 
</div>