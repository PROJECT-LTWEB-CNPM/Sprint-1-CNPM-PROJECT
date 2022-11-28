const HTTP = "http:";
function getHostname(url) {
	const protocol = $(location).attr('protocol');
	const m = url.match(/^http:\/\/[^/]+/);
	const hostname = m ? m[0] : null;
	if (protocol === HTTP) {
		return hostname + '/CoursesRegistrationApp';
	}
	return hostname;
}

function softDeleteUser() {
	let personId = "";
	const deleteBtns = $('.btn-delete');


	deleteBtns.click((e) => {
		personId = e.currentTarget.dataset.bsId;
	})

	$('.btn-confirm-delete').click(() => {
		const url = getHostname($(location).attr('href'));
		$.ajax
			({
				type: "GET",
				url: `${url}/admin/users/delete`,
				data: { personId },
				success: function(html) {
					alert(html);
					window.location.reload();
				}
			})
	})
}

softDeleteUser();


