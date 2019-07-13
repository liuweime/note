<?php

class ListNode {

	public $val = 0;

	public $next = null;

	function __construct($val) { 
		$this->val = $val;
	}
}

/**
 * 
 */
class Linked
{
	public $head;
	public $size;

	public function __construct() {
		$this->head = new ListNode(null);
		$this->size = 0;
	}

	
	public function addNode(ListNode $node) {
		if (is_null($this->head->next)) {
			$this->head->next = $node;
			$this->size++;

			return true;
		}

		$node->next = $this->head->next;
		$this->head->next = $node;
		$this->size++;
	}
}

/**
 * Definition for a singly-linked list.
 * class ListNode {
 *     public $val = 0;
 *     public $next = null;
 *     function __construct($val) { $this->val = $val; }
 * }
 */
class Solution {

    /**
     * @param ListNode $l1
     * @param ListNode $l2
     * @return ListNode
     */
    function addTwoNumbers($l1, $l2) {
    	
    	$dec = 0;
    	$ListNode = new ListNode(0);
    	$target = $ListNode;
    	while ($l1 !== null || $l2 !== null) {

    		$item1 = is_null($l1) ? 0 : $l1->val;
    		$item2 = is_null($l2) ? 0 : $l2->val;
    		$sum = $item1 + $item2 + $dec;

    		$dec = floor($sum / 10);
    		$sum = $sum % 10;
    		
    		$target->next = new ListNode($sum);;
    		$target = $target->next;
    		
    		if (!is_null($l1)) {
				$l1 = $l1->next;
    		}
    		if (!is_null($l2)) {
    			$l2 = $l2->next;	
    		}
    	}

    	if ($dec > 0) {
    		$target->next = new ListNode($dec);

    	}



    	return $ListNode->next;
    }


    public function addTwoNumbersTwo($l1, $l2) {
    	$dec = 0;
		$ListNode = null;
		while ($l1 !== null || $l2 !== null) {

			$item1 = is_null($l1) ? 0 : $l1->val;
			$item2 = is_null($l2) ? 0 : $l2->val;
			$sum = $item1 + $item2 + $dec;
			if ($sum >= 10) {
				$sum = $sum % 10;
				$dec = 1;
			} else {
				$dec = 0;
			}
			$node = new ListNode($sum);
			if (is_null($ListNode)) {
				$ListNode = $node;
			} else {
				// $node->next = $ListNode;
				$tmp = $ListNode;
				while ($tmp->next !== null) {
					$tmp = $tmp->next;
				}
				$tmp->next = $node;
			}
			
			if (!is_null($l1)) {
				$l1 = $l1->next;
			}
			if (!is_null($l2)) {
				$l2 = $l2->next;	
			}
		}

		if ($dec === 1) {
			$node = new ListNode($dec);
			$tmp = $ListNode;
			while ($tmp->next !== null) {
				$tmp = $tmp->next;
			}
			$tmp->next = $node;
		}

		return $ListNode;
    }

    public function addTwoNumbersThree($l1, $l2) {
    	
    	$dec = 0;
    	$ListNode = null;
    	while ($l1 !== null || $l2 !== null) {

    		$item1 = is_null($l1) ? 0 : $l1->val;
    		$item2 = is_null($l2) ? 0 : $l2->val;
    		$sum = $item1 + $item2 + $dec;
    		if ($sum >= 10) {
    			$sum = $sum % 10;
    			$dec = 1;
    		} else {
    			$dec = 0;
    		}
    		$node = new ListNode($sum);
    		if (is_null($ListNode)) {
    			$ListNode = $node;
    		} else {
    			$node->next = $ListNode;
    			$ListNode = $node;
    		}
    		
    		if (!is_null($l1)) {
				$l1 = $l1->next;
    		}
    		if (!is_null($l2)) {
    			$l2 = $l2->next;	
    		}
    	}

    	if ($dec === 1) {
    		$node = new ListNode($dec);
    		$node->next = $ListNode;
    		$ListNode = $node;
    	}

    	// 反转链表
    	$node = null;
    	while ($ListNode !== null) {
    		$pointer = $ListNode->next;

    		$ListNode->next = $node;
    		$node = $ListNode;

    		$ListNode = $pointer;
    	}
    	

    	return $node;
    }
}


// 342 + 465 = 
$a = [2,4,3];   
$b = [5,6,6]; 
// 7 0 8 1

// $a = [3,4,2];   
// $b = [4,6,5];

$nodei = new ListNode(2);
$nodej = new ListNode(4);
$nodem = new ListNode(3);
$nodei->next = $nodej;
$nodej->next = $nodem;

$nodex = new ListNode(5);
$nodey = new ListNode(6);
$nodez = new ListNode(6);
// $nodeh = new ListNode(1);
$nodex->next = $nodey;
$nodey->next = $nodez;
// $nodez->next = $nodeh;

$s = new Solution();
$node = $s->addTwoNumbers($nodei, $nodex);

print_r($node);
