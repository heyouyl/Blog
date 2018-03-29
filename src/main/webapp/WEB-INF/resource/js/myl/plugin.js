function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
	return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}
function parseShortcode(content) {
	return content
			.replace(
					/(?:<p>)?\[(?:wp_)?caption([^\]]+)\]([\s\S]+?)\[\/(?:wp_)?caption\](?:<\/p>)?/g,
					function(a, b, c) {
						var id, align, classes, caption, img, width;

						id = b.match(/id=['"]([^'"]*)['"] ?/);
						if (id) {
							b = b.replace(id[0], '');
						}

						align = b.match(/align=['"]([^'"]*)['"] ?/);
						if (align) {
							b = b.replace(align[0], '');
						}

						classes = b.match(/class=['"]([^'"]*)['"] ?/);
						if (classes) {
							b = b.replace(classes[0], '');
						}

						width = b.match(/width=['"]([0-9]*)['"] ?/);
						if (width) {
							b = b.replace(width[0], '');
						}

						c = trim(c);
						img = c
								.match(/((?:<a [^>]+>)?<img [^>]+>(?:<\/a>)?)([\s\S]*)/i);

						if (img && img[2]) {
							caption = trim(img[2]);
							img = trim(img[1]);
						} else {
							// old captions shortcode style
							caption = trim(b).replace(/caption=['"]/, '')
									.replace(/['"]$/, '');
							img = c;
						}

						id = (id && id[1]) ? id[1].replace(/[<>&]+/g, '') : '';
						align = (align && align[1]) ? align[1] : 'alignnone';
						classes = (classes && classes[1]) ? ' '
								+ classes[1].replace(/[<>&]+/g, '') : '';

						if (!width && img) {
							width = img.match(/width=['"]([0-9]*)['"]/);
						}

						if (width && width[1]) {
							width = width[1];
						}

						if (!width || !caption) {
							return c;
						}

						width = parseInt(width, 10);

						return '<div class="mceTemp"><dl id="' + id
								+ '" class="wp-caption ' + align + classes
								+ '" style="width: ' + width + 'px">'
								+ '<dt class="wp-caption-dt">' + img
								+ '</dt><dd class="wp-caption-dd">' + caption
								+ '</dd></dl></div>';
					});
}
function getShortcode(content) {
	return content
			.replace(
					/(?:<div [^>]+mceTemp[^>]+>)?\s*(<dl [^>]+wp-caption[^>]+>[\s\S]+?<\/dl>)\s*(?:<\/div>)?/g,
					function(all, dl) {
						var out = '';

						if (dl.indexOf('<img ') === -1) {
							// Broken caption. The user managed to drag the
							// image out?
							// Try to return the caption text as a paragraph.
							out = dl.match(/<dd [^>]+>([\s\S]+?)<\/dd>/i);

							if (out && out[1]) {
								return '<p>' + out[1] + '</p>';
							}

							return '';
						}

						out = dl
								.replace(
										/\s*<dl ([^>]+)>\s*<dt [^>]+>([\s\S]+?)<\/dt>\s*<dd [^>]+>([\s\S]*?)<\/dd>\s*<\/dl>\s*/gi,
										function(a, b, c, caption) {
											var id, classes, align, width;

											width = c.match(/width="([0-9]*)"/);
											width = (width && width[1]) ? width[1]
													: '';

											classes = b
													.match(/class="([^"]*)"/);
											classes = (classes && classes[1]) ? classes[1]
													: '';
											align = classes
													.match(/align[a-z]+/i)
													|| 'alignnone';

											if (!width || !caption) {
												if ('alignnone' !== align[0]) {
													c = c.replace(/><img/,
															' class="'
																	+ align[0]
																	+ '"><img');
												}
												return c;
											}

											id = b.match(/id="([^"]*)"/);
											id = (id && id[1]) ? id[1] : '';

											classes = classes
													.replace(
															/wp-caption ?|align[a-z]+ ?/gi,
															'');

											if (classes) {
												classes = ' class="' + classes
														+ '"';
											}

											caption = caption
													.replace(/\r\n|\r/g, '\n')
													.replace(
															/<[a-zA-Z0-9]+( [^<>]+)?>/g,
															function(a) {
																// no line
																// breaks inside
																// HTML tags
																return a
																		.replace(
																				/[\r\n\t]+/,
																				' ');
															});

											// convert remaining line breaks to
											// <br>
											caption = caption.replace(
													/\s*\n\s*/g, '<br />');

											return '[caption id="' + id
													+ '" align="' + align
													+ '" width="' + width + '"'
													+ classes + ']' + c + ' '
													+ caption + '[/caption]';
										});

						if (out.indexOf('[caption') === -1) {
							// the caption html seems broken, try to find the
							// image that may be wrapped in a link
							// and may be followed by <p> with the caption text.
							out = dl
									.replace(
											/[\s\S]*?((?:<a [^>]+>)?<img [^>]+>(?:<\/a>)?)(<p>[\s\S]*<\/p>)?[\s\S]*/gi,
											'<p>$1</p>$2');
						}

						return out;
					});
}