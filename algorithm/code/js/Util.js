export function exchNode(i, j) {
    const oINode = $('#output span').eq(i);
    const oJNode = $('#output span').eq(j);

    const sTargetColor = '#FBC02D';
    const sNormalColor = '#283593';
    const iAnimateTime = 500;

    oINode.css('background', sTargetColor);
    oJNode.css('background', sTargetColor);

    oINode.animate({'left': oJNode.position().left}, iAnimateTime, () => oINode.css('background', sNormalColor))
    oJNode.animate({'left': oINode.position().left}, iAnimateTime, () => {
        oJNode.css('background', sNormalColor)
        const b = oINode.clone(true),
              a = oJNode.replaceWith(b);
              
        oINode.replaceWith(a);
    });
}
